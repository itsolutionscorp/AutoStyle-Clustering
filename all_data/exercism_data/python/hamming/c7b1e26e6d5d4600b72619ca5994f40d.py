def distance(dna_1,dna_2):
	distance = 0
	for i in range(len(dna_1)):
		if dna_1[i] != dna_2[i]:
			distance += 1
	return distance
