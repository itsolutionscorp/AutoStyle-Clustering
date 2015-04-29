def to_rna(strand):
	trans = {
		'G': 'C',
		'C': 'G',
		'T': 'A',
		'A': 'U'
	}

	return ''.join([trans[i] for i in strand])
