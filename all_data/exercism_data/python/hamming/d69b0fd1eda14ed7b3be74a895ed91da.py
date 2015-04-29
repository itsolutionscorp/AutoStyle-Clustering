def distance(dna, rna):
	total = 0
	for i in range(0, len(dna)):
		if dna[i] != rna[i]:
			total += 1
	return total
