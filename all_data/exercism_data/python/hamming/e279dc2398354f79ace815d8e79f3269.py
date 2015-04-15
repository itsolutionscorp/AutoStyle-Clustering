
def hamming(seq1, seq2):
	dist = 0
	shorter = min(len(seq1),len(seq2))
	longer = max(len(seq1),len(seq2))
	for i in range(shorter):
		if seq1[i] != seq2[i]:
			dist += 1
	dist += longer - shorter
	return dist
