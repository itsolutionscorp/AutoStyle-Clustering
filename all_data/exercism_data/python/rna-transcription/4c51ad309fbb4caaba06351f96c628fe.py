def to_rna(dna):
	mapDNAtoRNA = {
		'G': 'C',
		'C': 'G',
		'T': 'A',
		'A': 'U'
	}

	listDNA = list(dna)
	result = []
	for l in listDNA:
		result.append(mapDNAtoRNA[l])

	return "".join([mapDNAtoRNA[l] for l in list(dna)])
