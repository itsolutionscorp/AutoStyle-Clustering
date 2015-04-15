def distance(seq1, seq2):
	seq1 = seq1.upper()
	seq2 = seq2.upper()
	dcount = 0
	position = 0
	for bp in seq1:
		if seq2[position] != seq1[position]: dcount += 1
		position += 1
	return dcount
