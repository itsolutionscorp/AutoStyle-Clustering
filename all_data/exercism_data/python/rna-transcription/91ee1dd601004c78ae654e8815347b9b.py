def to_rna(dna):
	rna = ''
	for c in dna:
		if c == "G":
			rna = rna + 'C'
		elif c == 'C':
			rna = rna + 'G'
		elif c == 'T':
			rna = rna + 'A'
		elif c == 'A':
			rna = rna + 'U'	
	return rna
