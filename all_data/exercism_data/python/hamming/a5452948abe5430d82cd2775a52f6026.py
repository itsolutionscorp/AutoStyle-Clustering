def hamming(dna_1, dna_2):
	distance = abs(len(dna_1)-len(dna_2))
	
	for i in range(0, min(len(dna_1), len(dna_2))):
		if dna_1[i] != dna_2[i]:
			distance += 1
			
	return distance
