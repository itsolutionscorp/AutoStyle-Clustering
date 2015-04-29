def hamming(dna_base, dna_copy):
	hamming_num = 0
	min_len = min(len(dna_base),len(dna_copy))
	for i in range(min_len):
		if dna_base[i] != dna_copy[i]:
			hamming_num += 1

	return hamming_num + abs(len(dna_base) - len(dna_copy))
