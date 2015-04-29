def distance(dna1, dna2):
	"""
	Input: Two DNA strands as strings.
	Output: The Hamming Distance (# of different nucleotides) between each strand.
	"""
	hamming = 0
	for a, b in zip(dna1, dna2):			# Pair up strings dna1 (a) and dna2 (b) 
		if a != b:
			hamming += 1
	return hamming
