__author__ = 'benlarue'


def to_rna(inp):
	return_rna = ""
	dna = list(inp)
	rna = {"G": "C", "C": "G", "T": "A", "A": "U"}
	for d in dna:
		return_rna+=rna[d]
	return return_rna
