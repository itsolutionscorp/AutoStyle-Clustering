def hamming(strand_one, strand_two):
	count = 0
	for i, j in zip(strand_one, strand_two):
		count += i != j
	return count + abs(len(strand_one)-len(strand_two))
