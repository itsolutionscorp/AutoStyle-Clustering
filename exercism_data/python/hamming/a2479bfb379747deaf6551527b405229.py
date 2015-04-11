def distance(dna1, dna2):
	if len(dna1) == len(dna2):
		distance = 0

		for i, nucleotide in enumerate(dna1):
			if nucleotide != dna2[i]:
				distance += 1

		return distance
	else:
		raise ValueError('The Hamming distance is only defined for sequences of equal length.')
