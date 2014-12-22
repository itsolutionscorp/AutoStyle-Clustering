#!/opt/local/bin/python

import os
import sys
import numpy as np
import pp
import time
import shutil
import subprocess
import tempfile
import read_elki_results
from sklearn import metrics

def mysilhouette(feature_file, xi, minpts):
  cwd = os.getcwd()
  elki_dir = tempfile.mkdtemp(dir=cwd)
  subprocess.call(['java', '-jar', 'elki.jar', 'KDDCLIApplication', '-dbc.in', feature_file, '-algorithm', 'clustering.OPTICSXi', '-opticsxi.xi', str(xi), '-optics.minpts', str(minpts), '-out', elki_dir])
  features = numpy.loadtxt(feature_file)
  clusters = read_elki_results.read_elki(elki_dir, features.shape[0]) 
  shutil.rmtree(elki_dir)
  n = numpy.unique(clusters).shape[0]
  try:
    s = sklearn.metrics.silhouette_score(features, clusters)
  except Exception:
    s = 0
  return (s, n)

def main():
  feature_file = sys.argv[1]
  xi = float(sys.argv[2])
  job_server = pp.Server(8)
  minpts_start = 5
  minpts_step = 1
  jobs = []
  for i in range(0, 95):
    minpts = minpts_start + i * minpts_step
    jobs.append(
      ( minpts, job_server.submit(mysilhouette, (feature_file, xi, minpts), (), ("sklearn.metrics", "numpy", "os", "subprocess", "read_elki_results","shutil","tempfile",))))
    print "Task for: " + str(minpts) + " submitted"
  
  X = []; Y = []; Z = []; 
  for minpts, job in jobs:
    X.append(minpts)
    temp = job()
    Y.append(temp[0]) # Silhouette Score
    Z.append(temp[1]) # Number of clusters
    print "minpts: " + str(minpts) + " s: " + str(temp[0]) + " n: " + str(temp[1])

  m = np.max(Y)
  i = np.argmax(Y)
  print "--- Largest Silhouette Score: " + str(m) + " ---"
  print "--- minpts: " + str(X[i]) + " number_clusters: " + str(Z[i]) + " ---"
  print "###" + str(m) + "###" + str(X[i]) + "###" + str(Z[i]) + "###"

#np.savetxt("silhouette_x", X)
#np.savetxt("silhouette_y", Y)
#np.savetxt("silhouette_z", Z)

if __name__ == '__main__':
  start_time = time.time()
  main()
  print("--- %s seconds ---" % str(time.time() - start_time))

