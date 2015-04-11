# dna.py

import string

def to_rna(DNA):
	trans = string.maketrans('GCTA','CGAU')
	return DNA.translate(trans)
