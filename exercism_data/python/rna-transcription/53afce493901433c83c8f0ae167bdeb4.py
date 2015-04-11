def to_rna(dna):
	key = {'G':'C','C':'G','T':'A','A':'U'}
	return ''.join(key[c] for c in dna)
