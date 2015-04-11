def to_rna(dna):
	dna_to_rna_mapping = {
		'G': 'C',
		'C': 'G',
		'T': 'A',
		'A': 'U'
	}
	return ''.join(dna_to_rna_mapping.get(n) for n in dna.strip())
