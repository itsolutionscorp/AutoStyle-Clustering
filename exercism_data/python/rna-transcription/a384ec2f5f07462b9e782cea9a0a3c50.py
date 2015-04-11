def to_rna(strand):
	rna = ''
	for s in strand:
		if s == 'G':
			rna = rna + 'C'
		elif s == 'C':
			rna = rna + 'G'
		elif s == 'T':
			rna = rna + 'A'
		elif s == 'A':
			rna = rna + 'U'
	return rna
