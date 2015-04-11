def to_rna(input):
    
	rna = ''
	
	for each in input:
		if each == 'G':
			rna += 'C'
		elif each == 'C':
			rna += 'G'
		elif each == 'T':
			rna += 'A'
		elif each == 'A':
			rna += 'U'
	
	return rna
