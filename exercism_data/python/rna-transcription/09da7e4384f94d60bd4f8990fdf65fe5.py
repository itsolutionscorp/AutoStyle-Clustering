_rna = {'G':'C','C':'G','A':'U','T':'A'}

def to_rna(dna):
	return ''.join([_rna[s] for s in dna])
