import string

def to_rna(dna):
	table = string.maketrans('GCTA', 'CGAU')
	return dna.translate(table)
