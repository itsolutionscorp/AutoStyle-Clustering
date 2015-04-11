from string import maketrans

def to_rna(signs):
	translator = maketrans("GCTA","CGAU")
	return signs.translate(translator)
