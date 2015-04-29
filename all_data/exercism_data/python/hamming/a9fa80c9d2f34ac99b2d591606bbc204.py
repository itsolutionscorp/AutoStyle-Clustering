def distance(dna1, dna2):
	return [b1 != b2 for (b1, b2) in zip(dna1, dna2)].count(True)
