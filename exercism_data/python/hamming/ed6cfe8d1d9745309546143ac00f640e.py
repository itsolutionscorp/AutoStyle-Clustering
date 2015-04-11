def distance(strand1, strand2):
	hamming = 0
	for x in range(0, len(strand1)):
		if strand1[x] != strand2[x]:
			hamming += 1

	return hamming
