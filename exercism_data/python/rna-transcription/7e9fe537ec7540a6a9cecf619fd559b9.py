def to_rna(dna):
	c = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
	return ''.join([c[n] for n in dna])
