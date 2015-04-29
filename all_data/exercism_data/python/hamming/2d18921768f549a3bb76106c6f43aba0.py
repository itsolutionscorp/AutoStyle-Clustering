#!/usr//bin/python

def distance(orig, mutant):
	if orig is mutant:
		return 0
	else:
		marker = 0
		hamming_distance=0

		for nuc in orig:
			if nuc != mutant[marker]:
				hamming_distance += 1
			marker += 1

	return hamming_distance
		
