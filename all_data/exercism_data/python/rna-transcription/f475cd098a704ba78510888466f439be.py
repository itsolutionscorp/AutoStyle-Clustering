import string

RNA_TABLE = string.maketrans('GCTA', 'CGAU')

def to_rna(dna):
	return dna.translate(RNA_TABLE)
