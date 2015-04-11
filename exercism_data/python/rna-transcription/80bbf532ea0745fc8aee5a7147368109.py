import string
"""
	This program will translate the DNA charactures GCTA to the RNA charatures CGAU.
"""
tble = string.maketrans("GCTA", "CGAU")	
def to_rna(dna):
	return dna.translate(tble)
