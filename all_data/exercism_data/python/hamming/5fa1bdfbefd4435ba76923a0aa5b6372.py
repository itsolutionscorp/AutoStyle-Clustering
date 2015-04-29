def hamming(seqa,seqb):
	length_a = len(seqa)
	length_b = len(seqb)
	if length_a > length_b:
		distance = length_a - length_b
		for i in range(length_b):
			distance +=  (seqa[i]!=seqb[i])
	else:
		distance = length_b - length_a
		for i in range(length_a):
			distance +=  (seqa[i]!=seqb[i])
	return distance
