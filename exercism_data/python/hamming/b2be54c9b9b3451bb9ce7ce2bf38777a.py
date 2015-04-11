def hamming(dna1, dna2):
	errors = 0
	errors += abs(len(dna2) - len(dna1))
	for i in range(0, min(len(dna1),len(dna2))):
			if dna1[i] != dna2[i]:
				errors += 1
	return errors
