#!/usr/bin/env python

import numpy as np
import argparse
from scipy.cluster.vq import whiten
from sklearn import preprocessing

def num_concatenate(feature_vectors, out_file, no_centering, no_whitening, range_scaling):
    if range_scaling:
        range_scaler = preprocessing.MinMaxScaler(feature_range=(-1,1))
    first_vector = np.loadtxt(feature_vectors[0])
    if not no_centering:
        all_features = first_vector - np.mean(first_vector, 0)
    else:
        all_features = first_vector
    if len(all_features.shape) == 1:
        all_features = np.reshape(all_features, (all_features.shape[0], 1))
    for feature_file in feature_vectors[1:]:
        vector = np.loadtxt(feature_file)
        
        if not no_centering:
            vector = vector - np.mean(vector, 0)
        if len(vector.shape)==1:
            vector = np.reshape(vector, (vector.shape[0], 1))
        all_features = np.concatenate((all_features, vector), 1)
    if not no_whitening:
        all_features = np.nan_to_num(whiten(all_features))
    if range_scaling:
        all_features = range_scaler.fit_transform(all_features)
    np.savetxt(out_file, all_features, fmt='%.6f')
    
def string_concatenate(feature_vectors, out_file):
    first_vector = np.genfromtxt(feature_vectors[0], dtype='str', delimiter='\n')
    all_features = first_vector
    if len(all_features.shape) == 1:
        all_features = all_features[:, np.newaxis]
    for feature_file in feature_vectors[1:]:
        vector = np.genfromtxt(feature_file, dtype='str', delimiter='\n')
        if len(vector.shape) == 0:
            vector = np.reshape(vector, (1,1))
        if len(vector.shape) == 1:
            vector = vector[:, np.newaxis]
        all_features = np.concatenate((all_features, vector), 0)
    with open(out_file, 'w') as of:
        for s in all_features:
            of.write('%s\n'%s)

def main():
    parser = argparse.ArgumentParser(description='Take a number of feature vectors and concatenate them into one large feature vector.')
    parser.add_argument('-o', '--output_file', default='all_features.np', help='name of file to save concatenated features to')
    parser.add_argument('feature_vectors', nargs='+')
    parser.add_argument('-nw', '--no_whitening', action='store_true', help='include this to not whiten features after concatenating them')
    parser.add_argument('-nc', '--no_centering', action='store_true', help='include this to not center each feature before concatenating it')
    parser.add_argument('-rs', '--range_scaling', action='store_true', help='indluce this option to scale the range of each feature to [-1, 1]')
    parser.add_argument('-s', '--strings', action='store_true', help='include this option if the features are strings instead of numbers')
    args = parser.parse_args()
    out_file = args.output_file
    no_whitening = args.no_whitening
    no_centering = args.no_centering
    range_scaling = args.range_scaling
    strings = args.strings
    feature_vectors = args.feature_vectors
    if not strings:
        num_concatenate(feature_vectors, out_file, no_centering, no_whitening, range_scaling)
    else:
        string_concatenate(feature_vectors, out_file) 

if __name__ == '__main__':
    main()
