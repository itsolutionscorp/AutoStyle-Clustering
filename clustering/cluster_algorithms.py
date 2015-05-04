#!/usr/bin/env python

import sys
import argparse
import numpy as np
from sklearn.cluster import MeanShift, estimate_bandwidth, DBSCAN, SpectralClustering, KMeans
from cluster_validation import silhouette
from subprocess import call
from read_elki_results import read_elki

if __name__=='__main__':

    parser = argparse.ArgumentParser(description='Compute clusters from features using one of several algorithms.')
    parser.add_argument('algorithm', help='which clustering algorithm to use')
    parser.add_argument('data', help='location of file that contains features. Should be readable by numpy')
    parser.add_argument('-o', '--output_file', help='name of file to write clusters to')
    parser.add_argument('-k', type=int, help='number of clusters expected to occurs from the data')
    parser.add_argument('-e', '--ep', help='epsilon parameter for dbscan and optics')
    parser.add_argument('-m', '--min_sample', help='min sample argument for dbscan and optics')
    parser.add_argument('-x', '--xi', help='xi argument for optics')
    parser.add_argument('-b', '--beta', type=float, help='beta argument for weighted k-means')
    
    args = vars(parser.parse_args())
    features = np.loadtxt(args['data'])
    algorithm = args['algorithm']

    if algorithm == "kmeans":    
        k = args['k']    
        db = KMeans(n_clusters=k, n_init=30).fit(features)
        clusters = db.labels_
        np.savetxt(args['output_file'] if args['output_file'] else "kmeans_cluster.np", clusters)
        print np.unique(clusters).shape[0]
        print silhouette(features, clusters)

    if algorithm == "dbscan":
        ep = float(args['ep']) #default around .29
        min_sample = int(args['min_sample']) #default around 10
        db = DBSCAN(eps=ep, min_samples=min_sample).fit(features)
        clusters = db.labels_
        np.savetxt(args['output_file'] if args['output_file'] else "dbscan_clusters.np", clusters)
        print np.unique(clusters).shape[0]
        print silhouette(features, clusters)

    if algorithm == "dbscan_dist_matrix":
        ep = args['ep'] #default around .29
        min_sample = args['min_sample'] #default around 10
        features = features/(np.max(features) - np.min(features)) #TODO is this a smart thing?
        db = DBSCAN(eps=ep, min_samples=min_sample, metric='precomputed').fit(features)
        clusters = db.labels_
        np.savetxt(args['output_file'] if args['output_file'] else "dbscan_dist_clusters.np", clusters)
        print np.unique(clusters).shape[0]
        print silhouette(features, clusters)

    if algorithm == 'optics':
        xi = args['xi'] #default around .035
        min_sample = args['min_sample'] #default around 40
        call(['java', '-jar', 'elki.jar', 'KDDCLIApplication', '-dbc.in', sys.argv[2], '-algorithm', 'clustering.OPTICSXi', '-opticsxi.xi', xi, '-optics.minpts', min_sample, '-out', 'elki_out/'])
        clusters = read_elki('elki_out/', features.shape[0])
        np.savetxt(args['output_file'] if args['output_file'] else 'optics_clusters.np', clusters)
        print np.unique(clusters).shape[0]
        print silhouette(features, clusters)  

    if algorithm == "spectral":
        SIGMA = 50
        dist_matrix = features # sys.argv[2] is actually the distance matrix
        affinity = np.exp(-1*np.power(dist_matrix, 2)/(2*SIGMA**2))
        k = args['k']
        sc = SpectralClustering(n_clusters=k, affinity='precomputed').fit(affinity)
        clusters = sc.labels_
        np.savetxt(args['output_file'] if args['output_file'] else "spectral_clusters.np", clusters)
        print np.unique(clusters).shape[0]

    if algorithm == "mean_shift":
        bandwidth = estimate_bandwidth(features)
        ms = MeanShift(bandwidth=bandwidth)
        ms.fit(features)
        clusters = ms.labels_
        print silhouette(features, clusters)
        np.savetxt(args['output_file'] if args['output_file'] else "mean_shift_clusters.np", clusters)