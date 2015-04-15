def to_rna(dna):
	conversion = {'G': 'C', 'C': 'G','T': 'A','A': 'U'}
	rna = ''
	for i in dna:
		rna += conversion[i]
	return rna
