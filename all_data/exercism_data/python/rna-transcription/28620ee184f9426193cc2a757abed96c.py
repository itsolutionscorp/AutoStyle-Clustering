from string import maketrans
transtab = maketrans('GCTA', 'CGAU')

def to_rna(dna):
	
	return dna.translate(transtab)
