complements = {
	'G': 'C',
	'C': 'G',
	'T': 'A',
	'A': 'U'
}

def to_rna(dna):
	dna_list = list(dna)
	for index, char in enumerate(dna_list):
		dna_list[index] = complements[char]
	return ''.join(dna_list)
