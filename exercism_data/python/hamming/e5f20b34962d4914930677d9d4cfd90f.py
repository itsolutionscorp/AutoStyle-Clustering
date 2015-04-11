def distance(dna, rna):
	distance = 0
	for(i,j) in zip(dna, rna):
		if(i !=j):
			distance = distance + 1

	return distance
