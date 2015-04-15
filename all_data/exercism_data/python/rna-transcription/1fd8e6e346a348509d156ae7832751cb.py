def to_rna(dna):
	mapDNAtoRNA = {
		'G': 'C',
		'C': 'G',
		'T': 'A',
		'A': 'U'
	}

	return "".join([mapDNAtoRNA[l] for l in list(dna)])
