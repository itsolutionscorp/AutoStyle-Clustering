from string import maketrans
def to_rna(x):
	return x.translate(maketrans('GCTA', 'CGAU'))
