def to_rna(dna):
	dna_chars = list(dna)
	rna = ''
	
	for char in dna_chars:
		if char == 'G':
			rna += 'C'
		elif char == 'C':
			rna += 'G'
		elif char == 'T':
			rna += 'A'
		elif char == 'A':
			rna += 'U'
	return rna
