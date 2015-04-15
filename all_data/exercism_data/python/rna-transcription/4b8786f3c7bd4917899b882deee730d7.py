def to_rna(dna):
	rna = ''
	for letter in dna.upper():
		if letter == 'G':
			rna = rna + 'C'
		elif letter == 'C':
			rna = rna + 'G'
		elif letter == 'T':
			rna = rna + 'A'
		elif letter == 'A':
			rna = rna + 'U'
	return rna
