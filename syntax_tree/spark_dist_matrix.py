#/!usr/bin/python -tt
# Spark version of dist_matrix.py

import re
import sys
import numpy as np
import zss
import tree
import time
from pyspark import SparkContext

# Linearly weighted by depth (incomplete version)
def weighted_distance(A, B):
  try:
    if A and B:  
      resultA = A.split("##")
      resultB = B.split("##")
      if resultA[1] == resultB[1]:
        return 0
      depth = ((int(resultA[0]) + int(resultB[0])) / 2)
    else:
      C = A if A else B
      resultC = C.split("##")
      depth = int(resultC[0])
    lost = ( 1 - 0.1 * depth ) if depth < 10 else 0
    return 10 * lost
  except Exception:
    print "Error in weighted_distance: A: " + str(A) + " B: " + str(B)
    return 0

def compute(t, bTrees):
  try:
    # The following line is for the incomplete version of weighted by depth
    #return (t[0], t[1], zss.simple_distance(bTrees.value[t[0]], bTrees.value[t[1]], tree.MyNode.get_children, tree.MyNode.get_label, weighted_distance))
    return (t[0], t[1], zss.simple_distance(bTrees.value[t[0]], bTrees.value[t[1]]))
  except Exception as e:
    return (t[0], t[1], 0.0) # If error, return 0
  
def main():
  if len(sys.argv) != 3:
    print >> sys.stderr, "Usage: spark_dist_matrix.py <input_dir> <method>"
    exit(-1)

  sc = SparkContext(appName="DistanceMatrix")
  input_dir = sys.argv[1]
  with open(input_dir + "/array_index", "r") as f:
    indexes = map(str.rstrip, f.readlines())
    f.close()

  # Construct trees
  method = sys.argv[2]
  trees = construct_trees(input_dir, indexes, method)
  bTrees = sc.broadcast(trees)

  # Construct tree tuples
  n = len(trees)
  treeTuples = []
  for i in range(0, n):
    for j in range(i, n):
     treeTuples.append((i, j)) 

  # Construct RDD
  treeTuplesRDD = sc.parallelize(treeTuples, 32) # Set to 8 * cores
  result = treeTuplesRDD.map(lambda t: compute(t, bTrees)).collect()

  m = np.zeros(n * n)
  m.shape = (n, n)
  for t in result:
    m[t[0]][t[1]] = t[2]
    m[t[1]][t[0]] = t[2]
    
  # Record Result
  np.savetxt("dist_matrix", m, delimiter=" ", fmt="%.3f")

  
def construct_trees(input_dir, indexes, method):
  trees = []
  for i in indexes:
    # Change to tree.control_flow_tree for recluster 
    # Change to tree.weighted_syntax_tree for incomplete weighted by depth
    trees.append(tree.abstract_syntax_tree(input_dir + "/" + i + "_ast", method))
  return trees
  

if __name__ == '__main__':
  start_time = time.time()
  main()
  print("--- %s seconds ---" % str(time.time() - start_time))


