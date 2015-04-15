def to_rna(dna):
	
	from string import maketrans
	return dna.translate(maketrans('GCTA','CGAU'))
