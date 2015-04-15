def distance(strandA, strandB):

	hamming_distance = 0	

	#Compare each nucleotide and increase distance if not equal
	for nucleotideA, nucleotideB in zip(strandA, strandB):
		if nucleotideA != nucleotideB:
			hamming_distance += 1

	return hamming_distance
