def hamming(dna1, dna2):
	iterations = 0
	hamming = 0

	if len(dna2) < len(dna1):
		iterations = len(dna2)
		hamming = len(dna1) - len(dna2)
	else:
		iterations = len(dna1)
		hamming = len(dna2) - len(dna1)

	for i in range(iterations):
		if dna1[i] != dna2[i]:
			hamming += 1
		i+=1

	return hamming
