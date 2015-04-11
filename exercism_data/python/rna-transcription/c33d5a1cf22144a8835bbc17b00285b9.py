def to_rna(seq=""):
	table = "".maketrans("GCTA","CGAU")
	return seq.translate(table)
