def to_rna(dna):
	rna = ''
	for c_dna in dna:
		if c_dna == 'G':
			rna += 'C'
		elif c_dna == 'C':
			rna += 'G'
		elif c_dna == 'T':
			rna += 'A'
		elif c_dna == 'A':
			rna += 'U'
	return rna
