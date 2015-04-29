def distance(strand_1, strand_2):
	if len(strand_1) != len(strand_2):
		raise ValueError("The hamming distance is not defined for two strands of unequal length.")

	dist = 0
	for s1, s2 in zip(strand_1, strand_2):
		if s1 != s2:
			dist += 1

	return dist
