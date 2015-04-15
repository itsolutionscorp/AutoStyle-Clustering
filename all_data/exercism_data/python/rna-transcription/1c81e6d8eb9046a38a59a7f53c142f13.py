from string import maketrans

def to_rna(dna):
	rna_trans = maketrans('GCTA','CGAU')
	return dna.translate(rna_trans)
