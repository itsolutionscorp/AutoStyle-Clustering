def distance(a,b):
	hamming = 0
	for nuc_a, nuc_b in zip(a,b):
		if nuc_a != nuc_b:
			hamming += 1
	return hamming
