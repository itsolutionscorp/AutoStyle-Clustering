def to_rna(dna):
	'''
	Using Python's Dictionary Object
	'''
	mapping = {'G': 'C','C': 'G','T': 'A','A': 'U'}
	rna = ''
	for l in dna:
		rna += mapping[l]
	return rna
