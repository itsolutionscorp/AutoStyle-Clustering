def to_rna(dna_string):
	intab = "GCTA"
	outtab = "CGAU"
	trantab = str.maketrans(intab,outtab)
	
	return dna_string.translate(trantab)
			
