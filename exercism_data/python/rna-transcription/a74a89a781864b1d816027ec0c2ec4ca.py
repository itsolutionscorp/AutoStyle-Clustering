import string

def to_rna(str):
	return str.translate(string.maketrans("GCTA", "CGAU"))
