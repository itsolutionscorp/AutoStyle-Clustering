def to_rna(strand):
	rna_switch = {
		'G': 'C',
		'C': 'G',
		'T': 'A',
		'A': 'U',
	}

	strand = str(strand)
	rna_list = list(strand.upper())
	for index, char in enumerate(rna_list):
		if char in rna_switch.keys():
			rna_list[index] = rna_switch[char]
	
	return ''.join(rna_list)
