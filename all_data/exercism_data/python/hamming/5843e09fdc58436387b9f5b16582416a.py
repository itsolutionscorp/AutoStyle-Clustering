def distance(dna1, dna2):
	ham = 0
	if not len(dna1) == len(dna2):
		return
	for x in range (0, len(dna1)):
		if dna1[x] != dna2[x]:
			ham = ham + 1
	return ham
