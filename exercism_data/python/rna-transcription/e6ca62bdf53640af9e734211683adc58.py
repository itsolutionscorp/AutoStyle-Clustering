def to_rna(dna):
	dna_to_rna = {'G': 'C', 'C': 'G', 'T':'A', 'A':'U'}
	result = ''
	for e in dna:
		result += dna_to_rna[e]
	return result
