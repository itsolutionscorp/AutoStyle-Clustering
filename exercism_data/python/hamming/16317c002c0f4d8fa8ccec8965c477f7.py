def hamming(seq1, seq2):
	hamm = 0
	len1 = len(seq1)
	len2 = len(seq2)
	for i in range(min(len1, len2)):
		if seq1[i] != seq2[i]:
			hamm += 1
	return hamm + abs(len1 - len2)
