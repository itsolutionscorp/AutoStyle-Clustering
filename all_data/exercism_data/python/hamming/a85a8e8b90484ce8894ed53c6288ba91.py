def hamming(strand_one, strand_two):
	count = 0
	for i in range(min(len(strand_one), len(strand_two))):
		if strand_one[i] != strand_two[i]:
			count += 1
	return count + abs(len(strand_one)-len(strand_two))
