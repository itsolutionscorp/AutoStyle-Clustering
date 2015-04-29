def distance(dna1, dna2):
	n = len(dna1)
	for x in range(len(dna1)):
		if dna1[x] == dna2[x]:
			n-=1
	return n
