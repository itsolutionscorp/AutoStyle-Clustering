def distance(dna1, dna2):
	count = 0
	for acid1, acid2 in zip(dna1,dna2):
		if acid1 != acid2:
			count += 1
	return count
