#!/usr/bin/python
#!/opt/local/bin/python

import sys
import numpy as np
from sklearn import metrics

if __name__ == '__main__':
    
    clusters1 = np.loadtxt(sys.argv[1]).astype(int)
    clusters2 = np.loadtxt(sys.argv[2]).astype(int)

    if len(sys.argv) == 4:
        remove_negatives = sys.argv[3]
        if remove_negatives == '--no-negatives':
            clusters1 = np.delete(clusters1, np.where(clusters1<0)[0], axis=0)
            clusters2 = np.delete(clusters2, np.where(clusters1<0)[0], axis=0)
            clusters1 = np.delete(clusters1, np.where(clusters2<0)[0], axis=0)
            clusters2 = np.delete(clusters2, np.where(clusters2<0)[0], axis=0)

    print metrics.adjusted_rand_score(clusters1, clusters2)
