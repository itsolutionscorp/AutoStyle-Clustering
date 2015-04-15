def to_rna(dna):
	rna = ''

	translation = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
	for letter in dna:        
		rna += translation[letter]
	return rna
