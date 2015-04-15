def hamming(dna1, dna2):
	hamming_distance = abs(len(dna1) - len(dna2)) 
	for n1, n2 in zip(dna1, dna2):
		if n1 != n2: hamming_distance += 1
	return hamming_distance
