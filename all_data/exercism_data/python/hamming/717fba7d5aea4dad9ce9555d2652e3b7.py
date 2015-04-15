def hamming(dna,other):
	return sum(d != o for d, o in map(None, dna, other))
