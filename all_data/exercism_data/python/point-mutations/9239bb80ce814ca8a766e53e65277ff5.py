def hamming_distance(dna1, dna2):
	counter = 0
	min_len = min(len(dna1), len(dna2))
	for pos in xrange(min_len):
		if dna1[pos] != dna2[pos]: counter += 1
	return counter
