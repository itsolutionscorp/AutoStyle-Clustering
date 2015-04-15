def to_rna(dna):
	translation = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
	rna = ''
	for x in dna:
		rna += translation[x]
		
	return rna
