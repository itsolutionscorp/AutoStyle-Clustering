def distance(a_dna, b_dna):
	assert(len(a_dna) == len(a_dna))
	count = 0
	for i in range(len(a_dna)):
		if a_dna[i]	!= b_dna[i]:
			count += 1
	return count
