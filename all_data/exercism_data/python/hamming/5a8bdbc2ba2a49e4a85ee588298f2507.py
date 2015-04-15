def hamming(dna_sequence1, dna_sequence2):
	length_diff = abs(len(dna_sequence1) - len(dna_sequence2))
	end_of_shorter_sequence = min(len(dna_sequence1), len(dna_sequence2))
	hamming_distance = length_diff
	for index in range(end_of_shorter_sequence):
		if dna_sequence1[index] != dna_sequence2[index]:
			hamming_distance += 1
	return hamming_distance
