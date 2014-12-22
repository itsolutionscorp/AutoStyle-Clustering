#!/usr/bin/env python

import numpy as np
import os
import sys

if __name__ == '__main__':
	dir = sys.argv[1]
	index_file = sys.argv[2]
	files = (os.listdir(dir))
	files.sort()

	nums = []

	for fil in files:
		if fil[-3:] == '.rb':
			nums += [fil[:-3]]


	index = []
	with open(index_file, "w+") as outfile:
		for num in nums:
			outfile.write(num + "\n")


