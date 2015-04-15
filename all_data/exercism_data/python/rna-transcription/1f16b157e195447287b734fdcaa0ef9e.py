#/usr/bin/env python

from string import maketrans

def to_rna(seq):
	intab = "GCTA"
	outtab = "CGAU"
	transtab = maketrans(intab, outtab)
	return seq.translate(transtab)
	

	
