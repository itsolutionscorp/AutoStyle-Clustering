#!/usr/bin/python -tt

import Pycluster as pc
import numpy as np
import sys
import re

filename, n = sys.argv[1], int(sys.argv[2])
dist = np.loadtxt(filename, usecols=range(0, 799))

clustermap, error, nfound = pc.kmedoids(dist, n, npass=1000)
medoids =  {}
for i in clustermap:
    medoids[i] = medoids.get(i,0) + 1

output_filename = re.sub('\/[^\/]+$', '/kmedoids_result', filename)
np.savetxt(output_filename, clustermap, delimiter=" ", fmt="%d")

print clustermap
print "\n"
print medoids
print "nfound: " + str(nfound)
