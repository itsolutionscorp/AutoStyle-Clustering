def to_rna(dna):
	replacements = {'G': 'C', 'C':'G', 'T':'A', 'A':'U'}
	rna = [replacements[c] for c in dna]
	return ''.join(rna)
		
