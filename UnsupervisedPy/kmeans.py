# Author: Dr. Renato Cordeiro de Amorim, r.amorim@glyndwr.ac.uk
#
#k_means
#
#data
#    numpy.array data set. It should be standardized (for best results). Format: entities x features
#k
#    Number of clusters.
#replicates
#    Number of times you want to run K-Means. The method will return the clustering with smallest
#    K-Means output criterion (sum of distances between entities and respective centroids)
#init_centroids
#   Initial centroids. Format: centroids x features
#dist
#   Distance in use.
#p
#   The distance exponent of the Minkowski and Minkowski_pthPower distances. Not used if you select a different distance
#max_ite
#   The maximum number of iterations allowed in K-Means. It also uses max_ite as the maximum number of tries for a
#successful clustering in a given replication.
########################################################################################################################
#
# Intelligent K-Means
#
# That's an initialisation for K-Means that can be used to find good initial centroids as well as the number of clusters
# If you know the number of clusters just set Theta to zero and k to the desired number of clusters.
#
# Theta
#   Defines the minimum number of entities necessary in a cluster.
#
# More information:
# Mirkin, Boris. Clustering: a data recovery approach. CRC Press, 2012.

import random as rd
import numpy as np


class KMeans(object):

    def __init__(self, _my_math):
        self.my_math = _my_math

    def k_means(self, data, k, replicates=1, init_centroids=None, dist='SqEuclidean', p=None, max_ite=100):
            final_dist = float("inf")
            for replication_i in range(replicates):
                #loops up to max_ite to try to get a successful clustering for this replication
                for i in range(max_ite):
                    [u, centroids, ite, dist_tmp] = self.__k_means_batch(data, k, init_centroids, dist, p, max_ite)
                    #it also breaks the loop if the init_centroids have been defined (k-means is deterministic)
                    if u[0] != -1 or (init_centroids is not None):
                        break
                if u[0] == -1:
                    raise Exception('Cannot generate a single successful clustering')
                #given a successful clustering, check if its the best
                if dist_tmp < final_dist:
                    final_u = u[:]
                    final_centroids = centroids[:]
                    final_ite = ite
                    final_dist = dist_tmp
            return final_u, final_centroids, final_ite, final_dist

    def __k_means_batch(self, data, k, centroids=None, distance='SqEuclidean', p=None, max_ite=100):
            #runs K-Means a single time - batch
            #returns -1, -1, -1, -1 if there is an empty cluster
            n_entities = data.shape[0]
            if centroids is None:
                centroids = data[rd.sample(range(n_entities), k), :]
            previous_u = np.array([])
            ite = 1
            while ite <= max_ite:
                dist_tmp = np.zeros([n_entities, k])
                #assig each entity to a cluster
                for k_i in range(k):
                    dist_tmp[:, k_i] = self.my_math.get_distance(data, centroids[k_i, :], distance, p)
                u = dist_tmp.argmin(axis=1)
                #put the sum of distances to centroids in dist_tmp
                dist_tmp = np.sum(dist_tmp[np.arange(dist_tmp.shape[0]), u])
                #stop if there are no changes in the partitions
                if np.array_equal(u, previous_u):
                    return u, centroids, ite, dist_tmp
                #update centroids
                for k_i in range(k):
                    entities_in_k = u == k_i
                    #Check if cluster k_i has lost all its entities
                    if sum(entities_in_k) == 0:
                        return np.array([-1]), np.array([-1]), np.array([-1]), np.array([-1])
                    centroids[k_i, :] = self.my_math.get_center(data[entities_in_k, :], distance, p)
                previous_u = u[:]
                ite += 1

    def ik_means(self, data, k=None, theta=0, distance='SqEuclidean', p=None):
        initial_centroids = None
        n_entities_in_cluster = None
        original_data = data[:]
        data_center = self.my_math.get_center(data, distance, p)
        #sort data by the distance to data_center
        data = data[self.my_math.get_distance(data, data_center, distance, p).argsort(), :]
        while data.size > 0:
            n_entities = data.shape[0]
            t_centroid = data[n_entities - 1, :]
            while True:
                belongs_to_t_centroid = self.my_math.get_distance(data, t_centroid, distance, p) < self.my_math.get_distance(data, data_center, distance, p)
                new_t_centroid = self.my_math.get_center(data[belongs_to_t_centroid, :], distance, p)
                if np.array_equal(new_t_centroid, t_centroid):
                    break
                t_centroid = new_t_centroid[:]
            #given an anomalous pattern, check if its big enough
            if sum(belongs_to_t_centroid) > theta:
                if initial_centroids is None:
                    initial_centroids = t_centroid[:]
                    n_entities_in_cluster = np.array([sum(belongs_to_t_centroid)])
                else:
                    initial_centroids = np.vstack([initial_centroids, t_centroid[:]])
                    n_entities_in_cluster = np.hstack([n_entities_in_cluster, sum(belongs_to_t_centroid)])
            data = data[np.logical_not(belongs_to_t_centroid), :]
        #if k is known, get the k clusters with the highest number of entities
        if k is not None:
            if k < initial_centroids.shape[0]:
                initial_centroids = initial_centroids[n_entities_in_cluster.argsort()[n_entities_in_cluster.size-k: n_entities_in_cluster.size], :]
        [u, centroids, ite, dist_tmp] = self.__k_means_batch(original_data, initial_centroids.shape[0], initial_centroids, distance, p)
        return u, centroids, ite, dist_tmp, initial_centroids

    def kmeanspp(self, data, k, distance='SqEuclidean'):
        'NOT FINISHED'
        n_entities = data.shape[0]
        t_centroid = data[rd.sample(range(n_entities), 1), :]
        distances = self.my_math.get_distance(data, t_centroid)
        distances /= distances.sum()

