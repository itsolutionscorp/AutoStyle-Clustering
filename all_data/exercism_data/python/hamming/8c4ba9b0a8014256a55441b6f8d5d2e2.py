def distance(dna1, dna2):
	value = 0
	for d1, d2 in zip(dna1, dna2):
		if d1 != d2:
			value += 1
	return value
