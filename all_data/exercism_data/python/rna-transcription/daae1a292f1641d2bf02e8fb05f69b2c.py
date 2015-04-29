dna_to_rna_mapping = {
	'G': 'C',
	'C': 'G',
	'T': 'A',
	'A': 'U'
}

def to_rna(strand):
	converted = [dna_to_rna_mapping[x] for x in strand]
	return ''.join(converted)
