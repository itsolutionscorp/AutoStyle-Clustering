def distance(seq1, seq2):
	
	if len(seq1) != len(seq2):
		return "Sequences must have the same length."
	
	hamming_distance = 0
	
	for i in range(len(seq1)):
		if seq1[i] != seq2[i]:
			hamming_distance += 1
	
	return hamming_distance
