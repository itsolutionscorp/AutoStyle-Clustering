import string

MOL_TABLE = string.maketrans('GCTA', 'CGAU')

def to_rna(molecules):
	return molecules.translate(MOL_TABLE)
