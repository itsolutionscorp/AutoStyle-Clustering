#!/usr/bin/python -tt

import re
import sys
import pdb
import tree
import numpy as np
import zss # Install: pip install zss
import pp
import time

def main():
  input_dir = sys.argv[1]
  with open(input_dir + "/array_index", "r") as f:
    indexes = map(str.rstrip, f.readlines())
    f.close()
  trees = construct_trees(input_dir, indexes)
  dist_matrix = compute_dist_matrix_pp(indexes, trees)
  np.savetxt(input_dir + "/" + "weighted_dist_matrix", dist_matrix, delimiter=" ", fmt="%d")


# Linearly weighted by depth
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

def construct_trees(input_dir, indexes):
  trees = []
  for i in indexes:
    trees.append(tree.weighted_syntax_tree(input_dir + "/" + i + "_ast", "combine_anagrams")) # Weighted Syntac Tree
  return trees

# Defined for submitting task with pp
def tree_distance(tree1, tree2):
  return zss.simple_distance(tree1, tree2, tree.MyNode.get_children, tree.MyNode.get_label, weighted_distance)

def compute_dist_matrix_pp(indexes, trees):
  job_server = pp.Server(8)
  n = len(trees)
#n = 20
  m = np.zeros(n*n) 
  m.shape = (n, n)
  jobs = []
  for i in range(0, n):
    for j in range(i, n):
      jobs.append((i, j, job_server.submit(tree_distance, (trees[i], trees[j]), (weighted_distance,),("zss","tree","re",))))
      print "submit task for: " + str(i) + ", " + str(j)
  k, total = 0, n * (n+1) / 2
  for i, j, job in jobs:
    k += 1.0
    d = job()
    m[i][j] = d
    m[j][i] = d
    print "row: " + str(i) + "/" + indexes[i] + " column: " + str(j) + "/" + indexes[j] + " dist: " + str(d) + " | percent: " + str(k/total)
  return m

def compute_dist_matrix(indexes, trees):
  n = len(trees)
  m = np.zeros(n*n) 
  m.shape = (n, n)
  for i in range(0, n):
    for j in range(i, n):
      d = zss.simple_distance(trees[i], trees[j], tree.MyNode.get_children, tree.MyNode.get_label, weighted_distance)
      m[j][i] = d
      m[i][j] = d
      print "row: " + str(i) + "/" + indexes[i] + " column: " + str(j) + "/" + indexes[j] + " dist: " + str(d)
  return m

if __name__ == '__main__':
  start_time = time.time()
  main()
  print("--- %s seconds ---" % str(time.time() - start_time))


