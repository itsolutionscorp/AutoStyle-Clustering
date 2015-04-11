def to_rna(dna):
	
	from string import maketrans

	intab = 'GCTA'
	outtab = 'CGAU'
	trantab = maketrans(intab, outtab)
	return dna.translate(trantab)
