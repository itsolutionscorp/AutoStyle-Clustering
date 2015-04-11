def to_rna(dna):
	rna = ''
	
	for base in dna:
		if base == 'G':
			rna += 'C'
		elif base == 'C':
			rna += 'G'
		elif base == 'T':
			rna += 'A'
		elif base == 'A':
			rna += 'U'
		
	return rna
