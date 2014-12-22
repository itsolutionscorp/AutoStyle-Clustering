#/!usr/bin/python -tt

import sys
import numpy as np
from pyspark import SparkContext
from pyspark.mllib.clustering import KMeans


def parseVector(line):
    return np.array([float(x) for x in line.split(' ')])

if __name__ == "__main__":
    if len(sys.argv) != 3:
        print >> sys.stderr, "Usage: kmeans <file> <k>"
        exit(-1)
    sc = SparkContext(appName="KMeans")
    lines = sc.textFile(sys.argv[1])
    data = lines.map(parseVector).cache()
    k = int(sys.argv[2])
    model = KMeans.train(data, k)
    print "Final centers: " + str(model.clusterCenters)
    result = data.map(lambda p: model.predict(p)).collect()
    print str(result)
    np.savetxt("kmeans_result", result)
