comp = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
def to_rna(dna):
	return ''.join(comp[c] for c in dna)
