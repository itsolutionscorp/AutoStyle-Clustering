#!/usr/bin/env python

def distance(strand_1, strand_2):
	counter = 0
	if len(strand_1) !=  len(strand_2):
		return False
	else:
		for x in range(len(strand_1)):
			nucleotide_1 = strand_1[x]
			nucleotide_2 = strand_2[x]
			if nucleotide_1 != nucleotide_2:
				counter +=1
	return counter
	
