def to_rna(sequence):
	rna_dict = {"G":"C", "C":"G", "T":"A", "A":"U"}
	trans = ""
	for nuc in sequence:
		trans += rna_dict[nuc]
	return trans
