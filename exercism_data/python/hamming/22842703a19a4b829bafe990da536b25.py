def hamming(first_strand, second_strand):
	hamming_distance = abs(len(first_strand) - len(second_strand))
	for base1, base2 in zip(first_strand, second_strand):
		if base1 != base2:
			hamming_distance += 1
	return hamming_distance
