def to_rna(strand):

	from string import maketrans   # Required to call maketrans function.

	intab = "GCTA"
	outtab = "CGAU"
	trantab = maketrans(intab, outtab)
	
	return strand.translate(trantab)
