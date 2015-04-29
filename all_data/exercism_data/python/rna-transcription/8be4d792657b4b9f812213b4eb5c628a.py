def to_rna(dna):
	rna = ''
	for i in dna:
		if i == 'C':
			rna += 'G'
		elif i == 'G':
			rna += 'C'
		elif i == 'T':
			rna += 'A'
		else:
			rna += 'U'
	return rna
