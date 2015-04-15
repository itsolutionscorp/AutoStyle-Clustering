import numpy as np


class Ward(object):

    def __init__(self, _my_math):
        self.my_math = _my_math

    def ward(self, data, k=1):
        #starts by setting each entity with a different label
        original_k = data.shape[0]
        u = np.arange(original_k)
        centroids = data[:]
        #each cluster has one entity
        n_entities = np.ones(original_k)
        current_k = data.shape[0]
        while current_k > k:
            #calculate the distance between every two clusters
            min_dist = float('inf')
            #calculates only the upper triangle
            for k_i in range(original_k - 1):
                if n_entities[k_i] == 0:
                    continue
                for k_ii in range(k_i+1, original_k):
                    if n_entities[k_ii] == 0:
                        continue
                    dist_tmp = ((n_entities[k_i] * n_entities[k_ii]) / n_entities[k_i] + n_entities[k_ii]) * sum((centroids[k_i, :] - centroids[k_ii, :])**2)
                    if dist_tmp < min_dist:
                        min_dist = dist_tmp
                        k_1min = k_i
                        k_2min = k_ii
            #merge the two clusters with the smallest distance
            n_entities[k_1min] += n_entities[k_2min]
            n_entities[k_2min] = 0
            u[u == k_2min] = k_1min
            centroids[k_1min, :] = data[u == k_1min, :].mean(axis=0)
            current_k -= 1
        return u, centroids[np.unique(u), :]






