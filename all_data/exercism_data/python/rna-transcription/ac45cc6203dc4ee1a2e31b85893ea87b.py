complements = {
	'C': 'G',
	'G': 'C',
	'T': 'A',
	'A': 'U'
}

def to_rna(str):
	collect = []
	for gene in str:
		collect.append(complements[gene])
	return ''.join(collect)
