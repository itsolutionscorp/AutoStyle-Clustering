def to_rna(dna_string):
	trantab = str.maketrans("GCTA","CGAU")
	
	return dna_string.translate(trantab)
			
