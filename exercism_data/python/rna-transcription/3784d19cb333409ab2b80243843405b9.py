from string import maketrans

def to_rna(dna):
	trans = maketrans('GCTA','CGAU')
	rna = dna.translate(trans)
	return rna
	
	


	
