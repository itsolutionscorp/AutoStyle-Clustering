from string import maketrans

tbl = maketrans('GCTA','CGAU')
def to_rna(s):
	return s.translate(tbl)
