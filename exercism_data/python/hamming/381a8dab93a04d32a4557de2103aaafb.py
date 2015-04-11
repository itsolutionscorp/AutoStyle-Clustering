def distance(dna1, dna2):
	
	hamming = 0

	for i, j in zip(dna1, dna2):
		if i != j:
			hamming += 1
	return hamming
