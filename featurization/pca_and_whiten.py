#!/usr/bin/env python

import numpy as np
from scipy import linalg, cluster
from spectral_clustering import pca
import argparse

if __name__=='__main__':
    
    parser = argparse.ArgumentParser(description='Pca and whiten features')
    parser.add_argument('features', help='location of file that contains the features')
    parser.add_argument('-o', '--output_file', default='spectral_features.np', help='name of file to write spectral features to')
    parser.add_argument('-p', '--pca', type=int, help='number of dimensions to reduce the spectral features to')
    parser.add_argument('-w', '--whiten', action='store_true', help='whether or not to whiten the spectral features')
    
    args = vars(parser.parse_args())
    data = np.loadtxt(args['features'])
    
    if args['pca']:
        pca_dim = args['pca']
        data = pca(data, pca_dim)
    if args['whiten']:
        data = cluster.vq.whiten(data)
    
    np.savetxt(args['output_file'] if args['output_file'] else "whitened_data.np", data)
