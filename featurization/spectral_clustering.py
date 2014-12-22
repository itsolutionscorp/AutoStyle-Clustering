#!/usr/bin/env python

import sys
import numpy as np
from scipy import linalg, cluster
import argparse

def pca(matrix, dim):
    U, S, Vt = linalg.svd(matrix, full_matrices=False)
    V = Vt.T

    ind = np.argsort(S)[::-1]
    U = U[:, ind]
    S = S[ind]
    V = V[:, ind]

    S = np.diag(S)

    pca_matrix = np.dot(U[:, :dim], S[:dim, :dim])
    return pca_matrix


def calculate_spectral_features(dissimilarity):
    
    n = dissimilarity.shape[0]
    
    affinity = np.exp(-1*np.power(dissimilarity,2)/(2*SIGMA**2))
    np.fill_diagonal(affinity, 0)
    
    D = np.diagflat(np.sum(affinity, 1))
    root_D = linalg.sqrtm(D)
    
    laplacian = np.linalg.solve(root_D, np.dot(affinity, np.linalg.inv(root_D)))
#laplacian = np.eye(D.shape[0], D.shape[1]) - np.linalg.solve(root_D, np.dot(affinity, np.linalg.inv(root_D)))

    
    egvals, egvecs = np.linalg.eig(laplacian)
    idx = egvals.argsort()[-K:][::-1]
    #idx = egvals.argsort()[0:K] 
    
    egvals = egvals[idx]
    egvecs = egvecs[:,idx]
    
    norm_egvecs = egvecs/np.reshape((np.sum(np.abs(egvecs)**2, axis=1)**(1./2)), (n, 1)) #normalizes each eigenvector
    
    return np.real(norm_egvecs)

if __name__=='__main__':
    global K, SIGMA
    SIGMA = 150 #TODO: mess with this
    parser = argparse.ArgumentParser(description='Calculate spectral features.')
    parser.add_argument('dist_matrix', help='location of file that contains a distance matrix. Should be readable by numpy')
    parser.add_argument('k', type=int, help='number of clusters expected to occurs from the data')
    parser.add_argument('-o', '--output_file', default='spectral_features.np', help='name of file to write spectral features to')
    parser.add_argument('-p', '--pca', type=int, help='number of dimensions to reduce the spectral features to. Should be < k')
    parser.add_argument('-w', '--whiten', action='store_true', help='whether or not to whiten the spectral features')
    
    args = vars(parser.parse_args())
    data = np.loadtxt(args['dist_matrix'])
    K = args['k']
    spectral_features = calculate_spectral_features(data)
    
    if args['pca']:
        spectral_features = pca(spectral_features, args.p)
    if args['whiten']:
        spectral_features = cluster.vq.whiten(spectral_features)

    np.savetxt(args['output_file'], spectral_features, fmt='%.6f')
