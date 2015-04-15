def distance(strand1, strand2):

	hamming_distance = 0

	for i in range(0, len(strand1)):
		if strand1[i] != strand2[i]:
			hamming_distance += 1
	return hamming_distance
