def to_rna(dna):
	rna = ''
	key = {'G':'C','C':'G','T':'A','A':'U'}
	for char in dna:
		rna += key[i]
	return rna
