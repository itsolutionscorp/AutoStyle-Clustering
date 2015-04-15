def distance(strand_1, strand_2):
	if len(strand_1) != len(strand_2):
		raise ValueError("The hamming distance is not defined for two strands of unequal length.")

	return sum(s1 != s2 for s1, s2 in zip(strand_1, strand_2))
