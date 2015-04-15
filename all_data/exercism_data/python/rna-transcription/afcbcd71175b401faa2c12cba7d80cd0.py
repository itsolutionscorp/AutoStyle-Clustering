from string import maketrans

def to_rna(dna_string):
	intab = 'GCTA'
	outtab = 'CGAU'
	trantab = maketrans(intab, outtab)

	return(dna_string.translate(trantab)) 
