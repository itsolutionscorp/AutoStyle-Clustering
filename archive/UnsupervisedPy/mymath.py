# Author: Dr. Renato Cordeiro de Amorim, r.amorim@glyndwr.ac.uk
#
# Common math-related functions
#
import numpy as np


class MyMath(object):

    def is_number(self, x):
        try:
            float(x)
            return True
        except ValueError:
            return False

    def get_distance(self, x, y, distance=None, p=None, w=None):
        #sort out dimensions
        if x.ndim > 1:
            #at least one of them is a table, add over columns
            axis_tmp = 1
            n_features = x.shape[1]
        elif y.ndim > 1:
            axis_tmp = 1
            n_features = y.shape[1]
        else:
            axis_tmp = 0
            n_features = x.shape[0]
        if w is None:
            w = np.ones(n_features)
        if distance is None or distance == 'SqEuclidean':
            return (((x - y)**2)*w).sum(axis=axis_tmp)
            #
        if distance == 'Euclidean':
            return (((x - y)**2)*w).sum(axis=axis_tmp)**0.5
        #
        elif distance == 'Manhattan':
            return (abs(x - y)*w).sum(axis=axis_tmp)
        #
        elif distance == 'Minkowski' and self.is_number(p):
            if p == 0:
                raise Exception('Exponent p cannot be zero (Minkowski distance)')
            else:
                return ((abs(x - y)**p)*w).sum(axis=axis_tmp)**(1/p)
        #
        elif distance == 'Minkowski_pthPower' and self.is_number(p):
            return ((abs(x - y)**p)*w).sum(axis=axis_tmp)
        #
        elif distance == 'Chebyshev':
            return (abs(x-y)*w).max(axis=axis_tmp)
        else:
            raise Exception('Unrecognised distance')

    def get_center(self, x, distance=None, p=None, gradient=np.array([0.001])):
        if distance is None or distance == 'SqEuclidean':
            return x.mean(axis=0)
        elif distance == 'Manhattan':
            return np.median(x, axis=0)
        elif distance == 'Chebyshev':
            return (x.max(axis=0) - x.min(axis=0))/2
        elif (distance == 'Minkowski' or distance == 'Minkowski_pthPower') and self.is_number(p):
            gradient = gradient.repeat(x.shape[1])
            #starts the search from the mean
            x_center = x.mean(axis=0)
            distance_to_x_center = (abs(x - x_center)**p).sum(axis=0)
            nx_center = x_center + gradient
            distance_to_nx_center = (abs(x - nx_center)**p).sum(axis=0)
            gradient[distance_to_x_center < distance_to_nx_center] *= -1
            while True:
                nx_center = x_center + gradient
                distance_to_nx_center = (abs(x - nx_center)**p).sum(axis=0)
                gradient[distance_to_nx_center >= distance_to_x_center] *= 0.9
                x_center[distance_to_nx_center < distance_to_x_center] = nx_center [distance_to_nx_center < distance_to_x_center]
                distance_to_x_center[distance_to_nx_center < distance_to_x_center] = distance_to_nx_center[distance_to_nx_center < distance_to_x_center]
                if np.all(np.abs(gradient) < 0.0001):
                    return x_center
        else:
            raise Exception('Unrecognised distance')

    def compare_categorical_vectors(self, x, y):
        #simple comparison between vectors x and y using a confusion matrix
        #returns: accuracy in [0,1], the confusion matrix, base-zero indexes of categorical pairs
        max_n_categories = int(np.max([x.max(), y.max()]) + 1)
        #Create confusion matrix
        confusion_matrix = np.zeros([max_n_categories, max_n_categories])
        for i in range(max_n_categories):
            for ii in range(max_n_categories):
                confusion_matrix[i, ii] = sum(y[x == i] == ii)
        final_confusion_matrix = confusion_matrix[:]
        # Remove pairs from the table, from high to low
        pairs = np.zeros([max_n_categories, 2])
        r = 0
        for i in range(max_n_categories):
            [pairs[i, 0], pairs[i, 1]] = np.unravel_index(confusion_matrix.argmax(), confusion_matrix.shape)
            r += confusion_matrix[pairs[i, 0], pairs[i, 1]]
            confusion_matrix[pairs[i, 0], :] = -1
            confusion_matrix[:, pairs[i, 1]] = -1
        return r/np.max([x.size, y.size]), final_confusion_matrix, pairs

    def standardize(self, data, categorical_features=np.array([]), stand_type='range'):
        #Standarize a data set that is NxFeatures
        #If stand_type = std, it uses the corrected standard deviation
        if stand_type == 'range':
            data_range = data.max(axis=0) - data.min(axis=0)
            if any(data_range == 0):
                raise Exception('Division by zero (range)')
            else:
                return (data - data.mean(axis=0)) / data_range
        elif stand_type == 'std':
            data_std = data.std(axis=0, ddof=1)
            if any(data_std == 0):
                raise Exception('Division by zero (std)')
            else:
                return (data - data.mean(axis=0)) / data_std
        else:
            raise Exception('Unrecognised standardisation type')

    def get_distance_entity_to_centroid(self, u, data, centroids, distance=None, p=None):
        #return the sum of distances between entities and their respective centroids
        r = 0
        for k_i in range(u.max()+1):
            r += sum(self.get_distance(data[u == k_i, :], centroids[k_i, :], distance, p))
        return r

    def get_entity_with_min_distance(self, data, distance=None, p=None, w=None):
        #Returns the entity with the smallest sum of distances to all others, and the sum of distances
        if data.ndim == 1:
            return 0,0
        entities_n = data.shape[0]
        (min_distance, entity_index) = self.get_distance(data, data[0, :], distance, p, w).sum(),  0
        for i in range(1, entities_n):
            tmp_dist = self.get_distance(data, data[i, :], distance, p, w).sum()
            if tmp_dist < min_distance:
                (min_distance, entity_index) = tmp_dist,  i
        return entity_index, min_distance

