from string import maketrans
def to_rna(string):
	trantab=maketrans("GCTA","CGAU")
	return string.translate(trantab)
	
