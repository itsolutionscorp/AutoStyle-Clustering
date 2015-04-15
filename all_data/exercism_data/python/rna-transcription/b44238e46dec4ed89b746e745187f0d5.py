from string import maketrans

def to_rna(self):
	in_string = "GCTA"
	out_string = "CGAU"
	translation = maketrans(in_string, out_string)
	return self.translate(translation)
