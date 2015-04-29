# Author: Dr. Renato Cordeiro de Amorim, r.amorim@glyndwr.ac.uk
#
#Minkowski Weighted Partition Around Medoids - MWPAM
#
#data
#    numpy.array data set. It should be standardized (for best results). Format: entities x features
#k
#    Number of clusters.
#p
#   The distance exponent of the Minkowski_pthPower distances.
#use_build
#   If true, it uses a Minkowski based version of build to find initial medoids
#replicates
#    Number of times you want to run K-Means. The method will return the clustering with smallest
#    K-Means output criterion (sum of distances between entities and respective centroids)
#max_ite
#   The maximum number of iterations allowed. It also uses max_ite as the maximum number of tries for a
#successful clustering in a given replication.
########################################################################################################################
#
# More info:
# Amorim R.C. and Fenner, T., Weighting Features for Partition Around Medoids using the Minkowski Metric.
# IDA - Lecture Notes in Computer Science, 7619, pp. 35-44, 2012

import numpy as np
import random as rd
from wkmeans import WKMeans
from pam import PartitionAroundMedoids


class MWPAM(object):

    def __init__(self, _my_math):
        self.my_math = _my_math

    def __mwpam(self, data, k, p, medoids=None, weights=None, max_ite=100):
        #Runs MWPAM once
        #Returns -1 is it can't find k clusters.
        wkm = WKMeans(self.my_math)
        [n_entities, n_features] = data.shape
        if medoids is None:
            medoids = data[rd.sample(range(n_entities), k), :]
        if weights is None:
            weights = np.ones([k, data.shape[1]])/data.shape[1]
        previous_u = np.array([])
        ite = 1
        while ite <= max_ite:
            #assign each entity to a cluster
            #calculates all distances
            dist_tmp = np.zeros([n_entities, k])
            for k_i in range(k):
                dist_tmp[:, k_i] = self.my_math.get_distance(data, medoids[k_i, :], "Minkowski_pthPower", p, weights[k_i,:]**p)
            u = dist_tmp.argmin(axis=1)
            #put the sum of distances to centroids in dist_tmp
            dist_tmp = np.sum(dist_tmp[np.arange(n_entities), u])
            #stop if there are no changes in the partitions
            if np.array_equal(u, previous_u):
                return u, medoids, weights, ite, dist_tmp
            for k_i in range(k):
                #check is each cluster has at least one entity
                if (u==k_i).sum() == 0:
                    return np.array([-1]), np.array([-1]), np.array([-1]), np.array([-1]), np.array([-1])
                #update medoid
                medoids[k_i,:]=data[u==k_i,:][self.my_math.get_entity_with_min_distance(data[u==k_i,:], "Minkowski_pthPower", p, weights[k_i,:])[0]]
            #update weights
            weights = wkm._get_dispersion_based_weights(data, medoids, k, p, u, n_features, 'Minkowski_pthPower', p, 0.01)
            previous_u = u[:]
            ite += 1

    def mwpam(self, data, k, p, use_build=True, replicates=1, max_ite=100):
        if use_build == True:
            PAM = PartitionAroundMedoids(self.my_math)
            return self.__mwpam(data, k, p, PAM._build(data, k, 'Minkowski_pthPower', p), max_ite=max_ite)
        else:
            final_dist = float("inf")
            for i in range(replicates):
                #loops up to max_ite to try to get a successful clustering for this replication
                for i in range(max_ite):
                    [u, medoids, weights, ite, dist_tmp]= self.__mwpam(data, k, p, max_ite=max_ite)
                    if u[0] != -1:
                        break
                if u[0] == -1:
                    raise Exception('Cannot generate a single successful clustering')
                #given a successful clustering, check if its the best
                if dist_tmp < final_dist:
                    (final_u,final_medoids, final_weights, final_ite, final_dist) = u[:], medoids[:], weights[:], ite, dist_tmp
            return final_u, final_medoids, final_weights, final_ite, final_dist