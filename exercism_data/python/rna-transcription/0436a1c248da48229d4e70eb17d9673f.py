import string

code = string.maketrans('ATGC','UACG')

def to_rna(sequence):
	rna = sequence.translate(code)
	return rna
