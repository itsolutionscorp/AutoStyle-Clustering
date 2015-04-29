'''hamming.py
	created 25 Sept 2014
	by @jestuber
	'''
from itertools import izip

def hamming(strand1,strand2):
	
	distance = abs(len(strand1)-len(strand2))

	for p, q in izip(strand1,strand2):
		if p != q:
			distance += 1

	return distance
