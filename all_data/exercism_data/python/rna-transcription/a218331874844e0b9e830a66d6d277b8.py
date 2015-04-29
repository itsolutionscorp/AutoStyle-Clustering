def to_rna(dna):
	complements = {
		'G': 'C',
		'C': 'G',
		'T': 'A',
		'A': 'U'
	}
	rna = ""
	for c in dna:
		rna += complements[c]
	return rna
