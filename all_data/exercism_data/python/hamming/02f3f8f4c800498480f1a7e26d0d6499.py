def distance(strand1, strand2):

	hamming_distance = 0

	for a, b in zip(strand1, strand2):
		if a != b:
			hamming_distance += 1
	return hamming_distance
