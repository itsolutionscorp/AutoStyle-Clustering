def hamming(dna1, dna2):
	hamming_distance = abs(len(dna1) - len(dna2)) 
	min_len = min(len(dna1), len(dna2))
	for n in range(min_len):
		if dna1[n] != dna2[n]: hamming_distance += 1
	return hamming_distance
