def to_rna(strand):
	rna = ""
	for letter in strand:
		if letter == 'G':
			rna += 'C'
		if letter == 'C':
			rna += 'G'
		if letter == 'A':
			rna += 'U'
		if letter == 'T':
			rna += 'A'
	return rna
