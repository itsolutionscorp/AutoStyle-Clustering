from string import maketrans

table = maketrans("GCTA","CGAU")

def to_rna(string):
	return string.translate(table)
