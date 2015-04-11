def hamming(seqA, seqB):
	n = min(len(seqA), len(seqB))       # minimum length of the two sequences
	hdist = abs(len(seqA) - len(seqB))  # difference in length => starting distance

	for i in range(n):
		if seqA[i] != seqB[i]:
			hdist += 1

	return hdist
