from string import maketrans

table = maketrans('GCTA', 'CGAU')

def to_rna(molecules):
	return molecules.translate(table)
