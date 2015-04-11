from string import maketrans

intable = "GCTA"
outtable = "CGAU"
trantab = maketrans(intable, outtable)

def to_rna(dnastrand):
	return dnastrand.translate(trantab)
	
