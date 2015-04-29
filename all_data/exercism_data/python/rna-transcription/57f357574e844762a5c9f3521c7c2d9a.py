import string

def to_rna(strand):
	transTable=string.maketrans("GCTA","CGAU")
	return string.translate(strand,transTable)
