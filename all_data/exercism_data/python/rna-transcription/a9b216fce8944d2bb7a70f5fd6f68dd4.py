def to_rna(dna):
	map = {"G":"C", "C":"G", "T":"A", "A":"U"}
	return ''.join([map[x] for x in dna])
