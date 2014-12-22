#!/opt/local/bin/python
#!/usr/bin/python

import sys
import numpy as np
from sklearn import metrics

def silhouette(data, clusters):
    return metrics.silhouette_score(data, clusters)

if __name__ == '__main__':
    
    data = np.loadtxt(sys.argv[1])
    clusters = np.loadtxt(sys.argv[2])

    if len(sys.argv) == 4:
        remove_negatives = sys.argv[3]
        if remove_negatives == '--no-negatives':
            data = np.delete(data, np.where(clusters<0)[0], axis=0)
            clusters = np.delete(clusters, np.where(clusters<0)[0], axis=0)

    print silhouette(data, clusters.astype(int))
    #print metrics.silhouette_samples(data, clusters.astype(int))
