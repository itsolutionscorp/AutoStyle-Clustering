def to_rna(dna):
	rna = ''
	for c in dna:
		if c == 'G':
			rna += 'C'
		elif c == 'C':
			rna += 'G'
		elif c == 'T':
			rna += 'A'
		else:
			rna += 'U'
	return rna
