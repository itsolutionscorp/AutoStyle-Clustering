import string

def to_rna(letters):
	translate = {'G' : 'C', 'C' : 'G', 'T' : 'A', 'A' : 'U'}
	final = ""

	translation = string.maketrans('GCTA', 'CGAU')
	return letters.translate(translation)
