def to_rna(sequence):
	
	table = {
		'G': 'C',
		'C': 'G',
		'T': 'A',
		'A': 'U'
	}

	result = ""

	for x in sequence:
		result += table[x]

	return result
