def to_rna(dna):
	rna = ''
	for letter in dna:
		if letter == 'G':
			rna += 'C'
		if letter == 'C':
			rna += 'G'
		if letter == 'T':
			rna += 'A'
		if letter == 'A':
			rna += 'U'

	return rna
	
