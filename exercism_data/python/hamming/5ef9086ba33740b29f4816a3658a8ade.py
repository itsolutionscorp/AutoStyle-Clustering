def distance(dna1, dna2):
	if len(dna1) != len(dna2):
		raise ValueError('The Hamming distance is only defined for sequences of equal length.')

	distance = 0

	for nucleotide1, nucleotide2 in zip(dna1, dna2):
		if nucleotide1 != nucleotide2:
			distance += 1

	return distance
