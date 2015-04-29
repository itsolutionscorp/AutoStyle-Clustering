import string

def to_rna(str):
	return str.translate(str.maketrans('GCTA', 'CGAU'))

