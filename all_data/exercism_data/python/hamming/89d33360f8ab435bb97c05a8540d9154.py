def distance(dna_a, dna_b):
	distance = 0
	for a, b in zip(dna_a, dna_b):
		if a != b:
			distance += 1 
	return distance
