from string import maketrans

def to_rna(strand):
	transtab = maketrans("GCTA","CGAU")
	return strand.translate(transtab)
