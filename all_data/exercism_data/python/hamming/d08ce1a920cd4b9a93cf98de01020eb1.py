def hamming(dna_strand1, dna_strand2):
	hamming_distance = 0
	if len(dna_strand1) != len(dna_strand2):
		hamming_distance += abs(len(dna_strand1) - len(dna_strand2))

	for protein1, protein2 in zip (dna_strand1, dna_strand2):
		if protein1 != protein2:
			hamming_distance += 1
	
	return hamming_distance
