#!/usr/bin/python -tt

import sys
import numpy as np

def main():
  dist_matrix_file = sys.argv[1]
  old_index_file = sys.argv[2]
  new_index_file = sys.argv[3]
  created_matrix_file = sys.argv[4]

  dist_matrix = np.loadtxt(dist_matrix_file)
  old_indexes = np.loadtxt(old_index_file).tolist()
  new_indexes = np.loadtxt(new_index_file).tolist()

  dist_matrix = dist_matrix[np.in1d(old_indexes, new_indexes), :][:, np.in1d(old_indexes, new_indexes)]

  print dist_matrix

  print dist_matrix.shape
  
  np.savetxt(created_matrix_file, dist_matrix)

if __name__ == '__main__':
  main()
