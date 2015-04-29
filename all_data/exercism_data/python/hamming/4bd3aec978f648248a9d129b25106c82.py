def distance(strand_1, strand_2):
	hamming_distance = 0

	longer = strand_1 if len(strand_1) > len(strand_2) else strand_2
	shorter = strand_1 if len(strand_1) <= len(strand_2) else strand_1

	for i in xrange(0, len(shorter)):
		if shorter[i] != longer[i]:
			hamming_distance += 1

	hamming_distance += len(longer) - len(shorter)

	return hamming_distance
