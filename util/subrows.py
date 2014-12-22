#!/usr/bin/python -tt

import sys
import numpy as np

def main():
  feature_file = sys.argv[1]
  old_index_file = sys.argv[2]
  new_index_file = sys.argv[3]
  created_row_file = sys.argv[4]

  features = np.loadtxt(feature_file)
  old_indexes = np.loadtxt(old_index_file).tolist()
  new_indexes = np.loadtxt(new_index_file).tolist()

  features = features[np.in1d(old_indexes, new_indexes), :]

  print features
  print features.shape
  
  np.savetxt(created_row_file, features)

if __name__ == '__main__':
  main()
