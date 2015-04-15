def to_rna(dna_string):
	rna = ""
	for x in dna_string:
		if x == 'A':
			rna += 'U'	
		if x == 'U':
			rna += 'A'	
		if x == 'G':
			rna += 'C'
		if x == 'C':
			rna += 'G'
		if x == 'T':
			rna += 'A'
	return rna
