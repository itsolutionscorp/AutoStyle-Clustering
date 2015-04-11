def to_rna(dna):
	return ''.join(map(lambda x: {'G':'C', 'C':'G', 'T':'A', 'A':'U'}[x], dna))
