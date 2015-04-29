import string

def to_rna(dna_sequence):
	dna_characters = 'GCTA'
	rna_characters = 'CGAU'
	return dna_sequence.translate(string.maketrans(dna_characters, rna_characters))
