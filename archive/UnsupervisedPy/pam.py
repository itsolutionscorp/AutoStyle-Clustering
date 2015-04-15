# Author: Dr. Renato Cordeiro de Amorim, r.amorim@glyndwr.ac.uk
#
#Partition Around Medoids - PAM
#
#data
#    numpy.array data set. It should be standardized (for best results). Format: entities x features
#k
#    Number of clusters.
#replicates
#    Number of times you want to run PAM. The method will return the clustering with smallest
#    output criterion (sum of distances between entities and respective centroids)
#init_medoids
#   Initial medoids. Format: medoids x features
#dist
#   Distance in use. It can be any distance, even one that does not have a defined center.
#p
#   The distance exponent of the Minkowski and Minkowski_pthPower distances. Not used if you select a different distance
#max_ite
#   The maximum number of iterations allowed in PAM. It also uses max_ite as the maximum number of tries for a
#successful clustering in a given replication.
#
########################################################################################################################
#
# Build PAM
#
# That's an initialisation for PAM that attempts to find good initial medoids.
#
# For more information:
#
# Original version
#   Kaufman, L., Rousseeuw, P.J.: Finding groups in data: an introduction to cluster analysis. Wiley Online Library (1990)
#
# If using with the Weighted Minkowski distance, perhaps as an initalization for the Minkowski Weighted PAM
#   Amorim R.C. and Fenner, T., Weighting Features for Partition Around Medoids using the Minkowski Metric.
#   IDA - Lecture Notes in Computer Science, 7619, pp. 35-44, 2012
import numpy as np
import random as rd


class PartitionAroundMedoids(object):

    def __init__(self, _my_math):
        self.my_math = _my_math

    def __pam(self, data, k, medoids=None, distance='SqEuclidean', p=None, max_ite=100):
        #Runs PAM once
        #returns np.array([-1]) if there is an empty cluster
        n_entities = data.shape[0]
        if medoids is None:
            medoids = data[rd.sample(range(n_entities), k), :]
        previous_u = np.array([])
        ite = 1
        while ite <= max_ite:
            #assign each entity to a cluster
            #calculates all distances
            dist_tmp = np.zeros([n_entities, k])
            for k_i in range(k):
                dist_tmp[:, k_i] = self.my_math.get_distance(data, medoids[k_i, :], distance, p)
            u = dist_tmp.argmin(axis=1)
            #put the sum of distances to centroids in dist_tmp
            dist_tmp = np.sum(dist_tmp[np.arange(n_entities), u])
            #stop if there are no changes in the partitions
            if np.array_equal(u, previous_u):
                return u, medoids, ite, dist_tmp
            tmp = np.random.random([3,4])
            #update medoid
            for k_i in range(k):
                entities_in_k = u == k_i
                #Check if cluster k_i has lost all its entities
                if sum(entities_in_k) == 0:
                    return np.array([-1]), np.array([-1]), np.array([-1]), np.array([-1])
                #update the medoid
                medoids[k_i,:] = data[entities_in_k][self.my_math.get_entity_with_min_distance(data[entities_in_k], distance, p)[0]]
            previous_u = u[:]
            ite += 1

    def pam(self, data, k, replicates=1, init_medoids=None, dist='SqEuclidean', p=None, max_ite=100):
        final_dist = float("inf")
        for replication_i in range(replicates):
            #loops up to max_ite to try to get a successful clustering for this replication
            for i in range(max_ite):
                [u, medoids, ite, dist_tmp] = self.__pam(data, k, init_medoids, dist, p, max_ite)
                #it also breaks the loop if the init_centroids have been defined (pam is deterministic)
                if u[0] != -1 or (init_medoids is not None):
                    break
            if u[0] == -1:
                raise Exception('Cannot generate a single successful clustering')
            #given a successful clustering, check if its the best
            if dist_tmp < final_dist:
                final_u = u[:]
                final_medoids = medoids[:]
                final_ite = ite
                final_dist = dist_tmp
        return final_u, final_medoids, final_ite, final_dist

    def build_pam(self, data, k, dist='SqEuclidean', p=None, max_ite=100):
        init_medoids = self._build(data, k, dist, p)
        return self.__pam(data, k, init_medoids, dist, p, max_ite)

    def _build(self, data, k, dist='SqEuclidean', p=None):
        entity_index = self.my_math.get_entity_with_min_distance(data, dist, p)[0]
        (init_medoids, init_medoids_n) = np.array(data[entity_index, :]), 1
        np.delete(data, entity_index,0)
        # finds the entity that is the farthest away from all medoids
        while (init_medoids_n < k):
            if init_medoids_n == 1:
                dist_tmp = self.my_math.get_distance(data, init_medoids, dist, p)
            else:
                dist_tmp = self.my_math.get_distance(data, init_medoids[0, :], dist, p)
            for i in range(1, init_medoids_n):
                if init_medoids_n == 1:
                    dist_tmp += self.my_math.get_distance(data, init_medoids, dist, p)
                else:
                    dist_tmp += self.my_math.get_distance(data, init_medoids[i, :], dist, p)
            entity_index = dist_tmp.argmax()
            #add entity to medoids
            init_medoids_n += 1
            init_medoids = np.vstack((init_medoids, data[entity_index,:]))
            np.delete(data, entity_index, 0)
        return init_medoids











