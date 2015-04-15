def distance(dna1, dna2):
	distance = 0
	for i in range( len(dna1) ):
		if dna1[i] != dna2[i]:
			distance += 1
	return distance
