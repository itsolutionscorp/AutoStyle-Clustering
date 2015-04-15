
def to_rna(dna):
	return ''.join([{'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}[c] for c in dna])
