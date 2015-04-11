def distance(dna1, dna2):
	diff = 0
	for i in range(0, len(dna1)):
		diff += (dna1[i] != dna2[i])
	
	return diff
