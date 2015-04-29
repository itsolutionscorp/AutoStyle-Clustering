#This function takes a strand of DNA and returns its RNA transcript

import re

def to_rna(str):
	x = dict({'G':'C', 'C':'G', 'T':'A', 'A':'U'})
	
	pattern = re.compile("|".join(x.keys()))
	return pattern.sub(lambda m: x[m.group(0)], str)

	#lambda m: x[re.escape

