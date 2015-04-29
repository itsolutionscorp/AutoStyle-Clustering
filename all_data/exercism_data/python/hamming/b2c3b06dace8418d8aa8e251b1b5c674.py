def distance(dna_1, dna_2):
	if len(dna_1) != len(dna_2):
		raise ValueError('the lenght must be the same')
	else:	
		c = 0
		for i in range(len(dna_1)):
			if dna_1[i] != dna_2[i]: c += 1
		return  c
