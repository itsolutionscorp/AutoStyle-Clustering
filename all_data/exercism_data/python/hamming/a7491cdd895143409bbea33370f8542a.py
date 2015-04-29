from itertools import izip

def distance(rna1, rna2):
	dif = 0
	for pair in izip(rna1, rna2):
		if len(set(list(pair))) != 1:
			dif += 1
	return dif
