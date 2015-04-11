def to_rna(dna):
	dna=dna.replace('A','U')
	dna=dna.replace('T','A')
	for char in dna:
		if char=='G':
			char='C'
		elif char=='C':
			char='G'
	return dna
