from clustering import Clustering
import numpy as np
import time

np.seterr(all='raise')
cl = Clustering()
#
#load data and correct labels (y)
data = np.genfromtxt("iris.csv", delimiter=',')
#put labels in y
y = np.zeros(150)
y[50:100] = 1
y[100:150] = 2
#
# standardize data
data = cl.my_math.standardize(data)
#apply one algorithm at a time
####################################################################
print('PAM')
start = time.time()
[u, centroids, ite, dist_tmp] = cl.pam(data, 3, replicates=10)
print('Time elapsed: ', time.time()-start)
print('Accuracy: ', cl.my_math.compare_categorical_vectors(u, y)[0])
####################################################################
print('Build PAM')
start = time.time()
[u, medoids, ite, dist_tmp] = cl.build_pam(data, 3)
print('Time elapsed: ', time.time()-start)
print('Accuracy: ', cl.my_math.compare_categorical_vectors(u, y)[0])
####################################################################
print('Minkowski Weighted PAM')
start = time.time()
[u, medoids, weights, ite, dist_tmp] = cl.mwpam(data, 3, 1.1, False, 10)
print('Time elapsed: ', time.time()-start)
print('Accuracy: ', cl.my_math.compare_categorical_vectors(u, y)[0])
####################################################################
print('Minkowski Weighted PAM (Initialized with Minkowski Build)')
start = time.time()
[u, medoids, weights, ite, dist_tmp] = cl.mwpam(data, 3, 1.1)
print('Time elapsed: ', time.time()-start)
print('Accuracy: ', cl.my_math.compare_categorical_vectors(u, y)[0])
####################################################################
print('K-Means')
start = time.time()
[u, centroids, ite, dist_tmp] = cl.k_means(data, 3, replicates=10)
print('Time elapsed: ', time.time()-start)
print('Accuracy: ', cl.my_math.compare_categorical_vectors(u, y)[0])
####################################################################
print('iK-Means')
start = time.time()
[u, centroids, ite, dist_tmp, init_centroids] = cl.ik_means(data, 3)
print('Time elapsed: ', time.time()-start)
print('Accuracy: ', cl.my_math.compare_categorical_vectors(u, y)[0])
####################################################################
print('WK-Means')
start = time.time()
[u, centroids, weights, ite, dist_tmp] = cl.wk_means(data, 3, 1.1, replicates=10)
print('Time elapsed: ', time.time()-start)
print('Accuracy: ', cl.my_math.compare_categorical_vectors(u, y)[0])
####################################################################
print('MWK-Means')
start = time.time()
[u, centroids, weights, ite, dist_tmp] = cl.mwk_means(data, 3, 1.1, replicates=10)
print('Time elapsed: ',time.time()-start)
print('Accuracy: ', cl.my_math.compare_categorical_vectors(u, y)[0])
####################################################################
print('iMWK-Means')
start = time.time()
[u, centroids, weights, ite, dist_tmp] = cl.imwk_means(data, 1.1, 3)
print('Time elapsed: ', time.time()-start)
print('Accuracy: ', cl.my_math.compare_categorical_vectors(u, y)[0])
####################################################################
print('Ward')
start = time.time()
[u, centroids] = cl.ward(data, 3)
print('Time elapsed: ', time.time()-start)
print('Accuracy: ', cl.my_math.compare_categorical_vectors(u, y)[0])