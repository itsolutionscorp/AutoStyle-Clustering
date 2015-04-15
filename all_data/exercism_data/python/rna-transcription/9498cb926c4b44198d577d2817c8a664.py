from string import maketrans

def to_rna(dna):
	conversion = maketrans("GCTA","CGAU")
	return dna.translate(conversion)
