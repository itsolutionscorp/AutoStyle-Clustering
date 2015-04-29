conversions =  {'C': 'G', 'G': 'C', 'A': 'U', 'T':'A'}
def to_rna(dna):
	rna = []
	for c in dna:
		rna.append(conversions[c])
	return ''.join(rna)
