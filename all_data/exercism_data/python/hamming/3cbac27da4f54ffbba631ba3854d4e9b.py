def distance(strand_1, strand_2):
	if len(strand_1) != len(strand_2):
		raise ValueError("The hamming distance is not defined for two strands of unequal length.")

	dist = 0
	for loc in range(len(strand_1)):
		if strand_1[loc] != strand_2[loc]:
			dist += 1

	return dist
