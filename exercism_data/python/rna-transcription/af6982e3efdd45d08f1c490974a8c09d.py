def to_rna(reference):
	rna = ""
	for r in reference:
		if r == 'G':
			rna += 'C'
		elif r == 'C':
			rna += 'G'
		elif r == 'T':
			rna += 'A'
		elif r == 'A':
			rna += 'U'
	return rna
