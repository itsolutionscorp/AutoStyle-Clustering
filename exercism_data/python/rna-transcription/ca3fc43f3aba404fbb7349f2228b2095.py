def to_rna(dna):
	rna = ''
	for neuc in dna:
		if(neuc == 'G'):
			rna = rna + 'C'
		elif(neuc == 'C'):
			rna = rna + 'G'
		elif(neuc == 'T'):
			rna = rna + 'A'
		elif(neuc == 'A'):
			rna = rna + 'U'
	return rna
