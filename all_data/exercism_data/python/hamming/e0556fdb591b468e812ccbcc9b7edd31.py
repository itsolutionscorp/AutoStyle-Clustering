def hamming(dna_1, dna_2):
	# make sure that both strands are the same length
	dna_1, dna_2 = adjust_length(dna_1, dna_2)

	difference = 0
	for nuc_index in range(0, len(dna_1)):
		if dna_1[nuc_index] != dna_2[nuc_index]:
			difference += 1

	return difference

def adjust_length(dna_1, dna_2):
	if len(dna_1) == len(dna_2):
		return dna_1, dna_2

	if len(dna_1) > len(dna_2):
		while len(dna_1) > len(dna_2):
			dna_2 += '0'

	else:
		while len(dna_2) > len(dna_1):
			dna_1 += '0'

	return dna_1, dna_2
