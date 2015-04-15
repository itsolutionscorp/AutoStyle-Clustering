translator = {'G':'C','C':'G','T':'A','A':'U'}
def to_rna(dna):
	return ''.join(translator[c] for c in dna)
