#/!usr/bin/python -tt

import sys
import os
import numpy as np
import time
import betterast
from pyspark import SparkContext, SparkConf
import zss


def compute(t, bTrees):
  try:
    return (t[0], t[1], zss.simple_distance(bTrees.value[t[0]], bTrees.value[t[1]]))
  except Exception:
    return (t[0], t[1], 0.0)
  
def main():
  if len(sys.argv) != 3:
    print >> sys.stderr, "Usage: python_spark_dist_matrix.py <path to ast_text directory> <master-url of form spark://HOST:PORT>"
    exit(-1)

  conf = SparkConf().setAppName("PythonDistanceMatrix").setMaster(sys.argv[2])
  sc = SparkContext(conf=conf)
  directory = sys.argv[1].rstrip("/")
  files = [directory+"/"+f for f in os.listdir(directory) if (not f.startswith('.') and not os.path.isdir(directory+"/"+f))]

  # Construct trees
  trees = []
  for f in files:
    lines = open(f, "r").readlines()
    lines = [i.strip("\n") for i in lines]
    lines = [(int(i.split(":")[0]), i.split(":")[1]) for i in lines]
    trees.append(betterast.build_tree(lines))
  bTrees = sc.broadcast(trees)

  # Construct tree tuples
  n = len(trees)
  treeTuples = []
  for i in range(0, n):
    for j in range(i, n):
     treeTuples.append((i, j))

  # Construct RDD
  treeTuplesRDD = sc.parallelize(treeTuples) # Set to 8 * cores
  result = treeTuplesRDD.map(lambda t: compute(t, bTrees)).collect()

  m = np.zeros(n * n)
  m.shape = (n, n)
  for t in result:
    m[t[0]][t[1]] = t[2]
    m[t[1]][t[0]] = t[2]
    
  # Record Result
  np.savetxt("np_python_dist_matrix", m, delimiter=" ", fmt="%.3f")


if __name__ == '__main__':
  start_time = time.time()
  main()
  print("--- %s seconds ---" % str(time.time() - start_time))


