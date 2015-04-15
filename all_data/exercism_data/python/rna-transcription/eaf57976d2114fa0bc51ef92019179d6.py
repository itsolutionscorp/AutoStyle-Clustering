from string import maketrans 


string = 'GCTAGGGCC'
def to_rna(string):
	intab = 'GCTA'
	outtab= 'CGAU'
	return string.translate(maketrans(intab, outtab))
