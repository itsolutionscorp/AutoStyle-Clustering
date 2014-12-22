# Author: Dr. Renato Cordeiro de Amorim, r.amorim@glyndwr.ac.uk
#
#Minkowski Weighted K-Means and intelligent Minkowski Weighted K-Means
#
#data
#    numpy.array data set. It should be standardized (for best results). Format: entities x features
#k
#    Number of clusters.
#p
#   The distance exponent of the Minkowski_pthPower distance
#init_centroids
#   Initial centroids. Format: centroids x features
#init_weights
#   Initial weights. Format: number of clusters x features.
#replicates
#    Number of times you want to run WK-Means. The method will return the clustering with smallest
#    WK-Means output criterion (weighted sum of distances between entities and respective centroids)
#max_ite
#   The maximum number of iterations allowed in K-Means
#
########################################################################################################################
#
# More info:
#
#de Amorim, R.C., Mirkin, B.: Minkowski metric, feature weighting and anomalous cluster initializing in k-means
#clustering. Pattern Recognition 45(3), 1061-1075 (2012)

import numpy as np
from wkmeans import WKMeans


class MWKMeans(object):

    def __init__(self, _my_math):
        self.my_math = _my_math

    def mwk_means(self, data, k, p, init_centroids=None, init_weights=None, replicates=1, max_ite=100):
        #Minkowski Weighted K-Means
        wkm = WKMeans(self.my_math)
        return wkm.wk_means(data, k, p, init_centroids, init_weights, 'Minkowski_pthPower', replicates, p, max_ite)

    def imwk_means(self, data, p, k=None, theta=0):
        #intelligent Minkowski K-Means
        wkm = WKMeans(self.my_math)
        n_features = data.shape[1]
        initial_centroids = None
        initial_weights = None
        entities_per_cluster = None
        original_data = data[:]
        data_center = self.my_math.get_center(data, 'Minkowski_pthPower', p)
        distance_to_center = self.my_math.get_distance(data, data_center, 'Minkowski_pthPower', p).argsort()
        data = data[distance_to_center, :]
        equal_weights = np.ones(data.shape[1])/n_features
        while data.size > 0:
            n_entities = data.shape[0]
            t_centroid = data[n_entities - 1, :]
            previous_belongs_to_t_centroid = np.array([])
            t_weights = equal_weights
            while True:
                belongs_to_t_centroid = self.my_math.get_distance(data, t_centroid, 'Minkowski_pthPower', p, t_weights**p) < self.my_math.get_distance(data, data_center,'Minkowski_pthPower', p, t_weights**p)
                if sum(belongs_to_t_centroid) == 0:
                    belongs_to_t_centroid[data.shape[0]-1] = True
                nt_centroid = self.my_math.get_center(data[belongs_to_t_centroid, :], 'Minkowski_pthPower', p)
                if np.array_equal(t_centroid, nt_centroid) or np.array_equal(belongs_to_t_centroid, previous_belongs_to_t_centroid):
                    break
                t_centroid = nt_centroid[:]
                previous_belongs_to_t_centroid = belongs_to_t_centroid[:]
                #update weights
                t_weights = wkm._get_dispersion_based_weights(data, np.vstack([data_center, t_centroid]), 2, p, belongs_to_t_centroid.astype(int), n_features, 'Minkowski_pthPower', p, 0.01)[1, :]
            if sum(belongs_to_t_centroid) >= theta:
                if initial_centroids is None:
                    initial_centroids = t_centroid[:]
                    initial_weights = t_weights[:]
                    entities_per_cluster = sum(belongs_to_t_centroid)
                else:
                    initial_centroids = np.vstack([initial_centroids, t_centroid])
                    initial_weights = np.vstack([initial_weights, t_weights])
                    entities_per_cluster = np.hstack([entities_per_cluster, sum(belongs_to_t_centroid)])
            data = data[np.logical_not(belongs_to_t_centroid), :]
        if k is not None:
            sort_idx = entities_per_cluster.argsort()
            sort_idx = sort_idx[-k:]
            initial_centroids = initial_centroids[sort_idx, :]
            initial_weights = initial_weights[sort_idx, :]
        return self.mwk_means(original_data, initial_centroids.shape[0], p, initial_centroids, initial_weights)