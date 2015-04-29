from string import maketrans
def to_rna(str):
	strtrans = maketrans('ACGT', 'UGCA')
	return str.translate(strtrans)
