
from string import maketrans

key = maketrans("GCTA","CGAU")


def to_rna(string):

	return ''.join(letter.translate(key) for letter in string)
