#!/usr/bin/python
import sys
import shutil
import glob
import numpy as np

if __name__ == '__main__':

	file_dir = sys.argv[1]
	output_file = sys.argv[2]
	temp_file = 'temp'

	with open(temp_file, 'wb') as outfile:
		for filename in glob.glob(file_dir + '/*'):
			with open(filename) as readfile:
				shutil.copyfileobj(readfile, outfile)

	times = np.loadtxt(temp_file)
	clean_times = times[:,0][np.argsort(times[:,1])]
	np.savetxt(output_file, clean_times)
