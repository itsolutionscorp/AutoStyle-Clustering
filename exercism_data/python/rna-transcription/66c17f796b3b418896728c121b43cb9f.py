def to_rna(dna):
	map_dna_to_rna = {
		'G': 'C',
		'C': 'G',
		'T': 'A',
		'A': 'U'
	}
	return ''.join([map_dna_to_rna[d] for d in dna])
