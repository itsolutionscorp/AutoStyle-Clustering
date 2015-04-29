from string import maketrans
trans_table = maketrans('GCTA','CGAU')
def to_rna(dna):
	return dna.translate(trans_table)
