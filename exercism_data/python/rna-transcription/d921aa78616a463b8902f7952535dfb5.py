import string

def to_rna(dna):

	rna_translate = string.maketrans('GCTA','CGAU')
	dna = dna.upper() #just in case
	return dna.translate(rna_translate)
