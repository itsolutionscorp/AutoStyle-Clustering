from string import maketrans

def to_rna(string):
	translation = maketrans("GCTA", "CGAU")
	return string.translate(translation)
