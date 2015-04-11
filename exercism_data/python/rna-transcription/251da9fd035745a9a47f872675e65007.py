import string

def to_rna(strand):
	table = string.maketrans("ACGT", "UGCA")
	return strand.translate(table)
