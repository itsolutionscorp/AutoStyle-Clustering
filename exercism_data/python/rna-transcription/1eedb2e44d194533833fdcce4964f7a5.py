from string import maketrans
def to_rna(str):
	strtrans = maketrans('GCTA', 'CGAU')
	return str.translate(strtrans)
