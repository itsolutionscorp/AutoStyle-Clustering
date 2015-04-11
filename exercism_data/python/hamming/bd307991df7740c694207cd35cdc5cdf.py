def distance(seq1, seq2):
	distance = 0
	for i in range(len(seq1)):
		if seq1[i] != seq2[i]:
			distance += 1
	return distance
