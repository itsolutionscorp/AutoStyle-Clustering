#!/usr/bin/python -tt

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
  np.savetxt(input_dir + "/" + "dist_matrix", dist_matrix, delimiter=" ", fmt="%d")

def construct_trees(input_dir, indexes):
  trees = []
  for i in indexes:
    trees.append(tree.abstract_syntax_tree(input_dir + "/" + i + "_ast", "combine_anagrams"))
  return trees

def tree_distance(tree1, tree2):
  return zss.simple_distance(tree1, tree2)

def compute_dist_matrix_pp(indexes, trees):
  job_server = pp.Server(8)
  n = len(trees)
#n = 20
  m = np.zeros(n*n) 
  m.shape = (n, n)
  jobs = []
  for i in range(0, n):
    for j in range(i, n):
      jobs.append((i, j, job_server.submit(tree_distance, (trees[i], trees[j]), (),("zss",))))
      if i%10==0 and j==i:
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
      d = zss.simple_distance(trees[i], trees[j])
      m[j][i] = d
      m[i][j] = d
      print "row: " + str(i) + "/" + indexes[i] + " column: " + str(j) + "/" + indexes[j] + " dist: " + str(d)
  return m

if __name__ == '__main__':
  start_time = time.time()
  main()
  print("--- %s seconds ---" % str(time.time() - start_time))

