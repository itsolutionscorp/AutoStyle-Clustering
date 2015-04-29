#to_rna

#import string
from string import maketrans

def to_rna(string):
	#print string
	intab = 'GCTA'
	outtab = 'CGAU'
	return string.translate(maketrans(intab,outtab))
