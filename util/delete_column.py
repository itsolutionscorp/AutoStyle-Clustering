#!/usr/bin/python -tt

import sys
import numpy as np

def main():
  input_file = sys.argv[1]
  c = int(sys.argv[2])

  input_array = np.loadtxt(input_file)

  print input_array.shape

  output_array = np.delete(input_array, c, 1)

  print output_array.shape

  np.savetxt(input_file + "_new", output_array, delimiter=" ", fmt="%d")

if __name__ == '__main__':
  main()
