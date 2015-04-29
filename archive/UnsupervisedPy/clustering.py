import numpy as np
import random as rd
from mymath import MyMath
from kmeans import KMeans
from wkmeans import WKMeans
from mwkmeans import MWKMeans
from pam import PartitionAroundMedoids
from ward import Ward
from mwpam import MWPAM


class Clustering(object):

    def __init__(self):
        self.my_math = MyMath()

    def k_means(self, data, k, replicates=1, init_centroids=None, dist='SqEuclidean', p=None, max_ite=100):
        km = KMeans(self.my_math)
        return km.k_means(data, k, replicates, init_centroids, dist, p, max_ite)

    def ik_means(self, data, k=None, theta=0, distance='SqEuclidean', p=None):
        km = KMeans(self.my_math)
        return km.ik_means(data, k, theta, distance, p)

    def wk_means(self, data, k, beta, init_centroids=None, init_weights=None, distance='SqEuclidean', replicates=1, p=None, max_ite=100):
        wkm = WKMeans(self.my_math)
        return wkm.wk_means(data, k, beta, init_centroids, init_weights, distance, replicates, p, max_ite)

    def mwk_means(self, data, k, p, init_centroids=None, init_weights=None, replicates=1, max_ite=100):
        mwk = MWKMeans(self.my_math)
        return mwk.mwk_means(data, k, p, init_centroids, init_weights, replicates, max_ite)

    def imwk_means(self, data, p, k=None, theta=0):
        mwk = MWKMeans(self.my_math)
        return mwk.imwk_means(data, p, k, theta)

    def pam(self, data, k, replicates=1, init_medoids=None, dist='SqEuclidean', p=None, max_ite=100):
        _pam = PartitionAroundMedoids(self.my_math)
        return _pam.pam(data, k, replicates, init_medoids, dist, p, max_ite)

    def build_pam(self, data, k, dist='SqEuclidean', p=None, max_ite=100):
        _build_pam = PartitionAroundMedoids(self.my_math)
        return _build_pam.build_pam(data, k, dist, p, max_ite)

    def mwpam(self, data, k, p, use_build=True, replicates=1, max_ite=100):
        _mwpam = MWPAM(self.my_math)
        return _mwpam.mwpam(data, k, p, use_build, replicates, max_ite)

    def ward(self, data, k=1):
        _ward = Ward(self.my_math)
        return _ward.ward(data, k)