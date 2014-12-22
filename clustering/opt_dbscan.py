#!/opt/local/bin/python
#!/usr/bin/python

import sys
import numpy as np
import pp
import time
from sklearn import metrics
from sklearn.cluster import DBSCAN
import pdb

def mysilhouette(features, eps, min_samples):
  db = sklearn.cluster.DBSCAN(eps=eps, min_samples=min_samples).fit(features)
  clusters = db.labels_
  n = numpy.unique(clusters).shape[0]
  try:
    s = sklearn.metrics.silhouette_score(features, db.labels_)
  except Exception:
    s = 0
  return (s, n)

def main():
  features = np.loadtxt(sys.argv[1])
  job_server = pp.Server(8)
  dist_start = 0.1
  dist_step = 0.02
  sample_start = 5
  sample_step = 1
  jobs = []
  for i in range(0, 30):
    for j in range(0, 70):
      eps = dist_start + i * dist_step
      min_samples = sample_start + j * sample_step
      jobs.append(
          ( eps, min_samples, job_server.submit(mysilhouette, (features, eps, min_samples), (), ("sklearn.metrics", "sklearn.cluster","numpy",))))
      print "Task for: " + str(eps) + ", "  + str(min_samples) + " submitted"
  
  X = []; Y = []; Z1 = []; Z2 = []
  for eps, min_samples, job in jobs:
    X.append(eps)
    Y.append(min_samples)
    temp = job()
    Z1.append(temp[0]) # Silhouette Score
    Z2.append(temp[1]) # Number of clusters
    print "Row: " + str(eps) + " column: " + str(min_samples) + " s: " + str(temp[0]) + " n: " + str(temp[1])

  m = np.max(Z1)
  i = np.argmax(Z1)
  print "--- Largest Silhouette Score: " + str(m) + " ---"
  print "--- eps: " + str(X[i]) + " min_samples: " + str(Y[i]) + " number_clusters: " + str(Z2[i]) + " ---"

  np.savetxt("silhouette_x", X)
  np.savetxt("silhouette_y", Y)
  np.savetxt("silhouette_z1", Z1)
  np.savetxt("silhouette_z2", Z2)

if __name__ == '__main__':
  start_time = time.time()
  main()
  print("--- %s seconds ---" % str(time.time() - start_time))
