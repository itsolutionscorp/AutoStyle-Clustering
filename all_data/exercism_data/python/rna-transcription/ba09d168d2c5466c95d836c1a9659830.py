import string

DNA_TO_RNA = string.maketrans("GCTA", "CGAU")

def to_rna(i):
	return i.translate(DNA_TO_RNA)
	
