def distance(dna1, dna2):
	"""
	Input: Two DNA strands as strings.
	Output: The Hamming Distance (# of different nucleotides) between each strand.
	"""
	hamming = 0
	for i in range(0, len(dna1)):
		if dna1[i] != dna2[i]:
			hamming += 1
	return hamming
