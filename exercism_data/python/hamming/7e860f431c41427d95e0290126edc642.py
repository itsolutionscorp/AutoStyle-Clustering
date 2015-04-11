def hamming(dna1,dna2):
	hammond = 0
	for i, j in map(None, dna1, dna2):
		if i != j:
			hammond += 1
	return hammond
