import re

def to_rna(string):

	string = re.sub('[A]', 'U', string)
	string = re.sub('[T]', 'A', string)
	string = re.sub('[G]', 'g', string)
	string = re.sub('[C]', 'G', string)
	string = re.sub('[g]', 'C', string)

	return string
