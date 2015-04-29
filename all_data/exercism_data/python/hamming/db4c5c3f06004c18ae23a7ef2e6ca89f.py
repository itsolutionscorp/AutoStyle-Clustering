def distance(dna1, dna2):
	counter = 0
	for sign_dna1, sign_dna2 in zip(dna1,dna2):
		if sign_dna1 != sign_dna2:
			counter += 1
	return counter
