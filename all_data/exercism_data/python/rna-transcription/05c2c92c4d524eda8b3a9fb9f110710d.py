DNA_TO_RNA = {
	'G': 'C',
	'C': 'G',
	'T': 'A',
	'A': 'U'
}

def dna_to_rna(chromosome):
	return DNA_TO_RNA[chromosome]

def to_rna(dna):
	return ''.join(list(map(dna_to_rna, list(dna))))
