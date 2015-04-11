def to_rna(dna):
	rna = ''
	for base in dna:
		if base.upper() == 'G':
			base = 'C'
		elif base.upper() == 'A':
			base = 'U'
		elif base.upper() == 'C':
			base = 'G'
		elif base.upper() == 'T':
			base = 'A'
		rna += base
	return rna
