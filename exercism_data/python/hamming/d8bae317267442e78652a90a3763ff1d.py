def hamming(dna_base, dna_copy):
	#if len(dna_base) == 0 and len(dna_copy) == 0:
		#return 0
	hamming_num = 0
	min_len = min(len(dna_base),len(dna_copy))
	for i in range(min_len):
		if dna_base[i] is not dna_copy[i]:
			hamming_num += 1

	return hamming_num + abs(len(dna_base) - len(dna_copy))
