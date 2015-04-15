def to_rna(dna):
	translator = {'G':'C','C':'G','T':'A','A':'U'}
	rna = ''
	for c in dna:
		rna += translator[c]
	return rna
