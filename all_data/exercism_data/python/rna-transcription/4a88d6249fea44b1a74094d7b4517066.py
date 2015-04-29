def to_rna(nucleotide):
	dict = {"G":"C", "C":"G", "T":"A", "A":"U"}
	rna = ""
	for i in range(len(nucleotide)):
		rna += dict.get(nucleotide[i])
	return rna
