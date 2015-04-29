def hamming(strand_one, strand_two):
	diff = abs(len(strand_one) - len(strand_two))

	count = 0
	for index, val in enumerate(strand_one):
		if len(strand_two) > index and val != strand_two[index]:
			count += 1
	return count + diff
