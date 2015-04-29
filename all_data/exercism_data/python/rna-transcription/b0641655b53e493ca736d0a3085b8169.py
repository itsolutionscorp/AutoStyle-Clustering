convTable = {
	'G': 'C',
	'C': 'G',
	'T': 'A',
	'A': 'U'
}

def to_rna(dna):
	rna = ""
	for nuc in dna:
		rna += convTable[nuc]

	return rna
