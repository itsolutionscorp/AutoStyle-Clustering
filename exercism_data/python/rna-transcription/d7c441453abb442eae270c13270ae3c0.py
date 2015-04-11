def to_rna(dna_strand):

	rna_map = {
		'G': 'C',
		'C': 'G',
		'T': 'A',
		'A': 'U',
	}

	return ''.join(
		map(
			lambda x : rna_map[x],
			list(dna_strand)
		)
		)
