import string

def to_rna (dna_string):
	dna_nucleotides = "GCTA"
	rna_nucleotides = "CGAU"
	transtable = dna_string.maketrans(dna_nucleotides, rna_nucleotides)
	return dna_string.translate(transtable)
