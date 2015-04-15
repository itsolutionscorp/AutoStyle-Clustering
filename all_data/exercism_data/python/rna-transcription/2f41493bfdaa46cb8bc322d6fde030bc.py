def to_rna(dna):
	rna = ""
	trans = {
		"G":"C",
		"C":"G",
		"T":"A",
		"A":"U",
	}
	for nucleo in dna:
		rna += trans[nucleo]
	return rna
