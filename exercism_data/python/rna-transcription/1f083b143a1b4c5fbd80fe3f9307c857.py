def to_rna(strand):
	table = {"G":"C", "C":"G", "T":"A", "A":"U"}
	rna = ''
	for i in strand:
		rna += table[i]
	return rna
