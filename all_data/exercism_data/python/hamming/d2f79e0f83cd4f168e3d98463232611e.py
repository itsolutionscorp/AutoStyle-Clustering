def distance(seq1, seq2):
	h_distance = 0
	if len(seq1) == len(seq2):
		for strand1, strand2 in zip(seq1, seq2):
			if strand1 != strand2:
				h_distance += 1
	else:
		return -1			
	return h_distance
