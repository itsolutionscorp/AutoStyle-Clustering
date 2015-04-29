import re

def to_rna(str):
	str=str.upper()
	str=re.sub('A', 'U', str)
	str=re.sub('T', 'A', str)
	str=re.sub('C', '*', str)
	str=re.sub('G', 'C', str)
	str=re.sub('\*', 'G', str)
	return str
