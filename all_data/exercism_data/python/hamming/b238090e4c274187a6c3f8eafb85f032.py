def distance(strand1, strand2):
	dist = 0
	for i in range(len(strand1)):
		if strand1[i] != strand2[i]:
			dist += 1
	return dist
