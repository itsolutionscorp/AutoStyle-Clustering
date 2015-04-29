def to_rna(dna):
	rna = { "G": "C",
			"C": "G", 
			"T": "A", 
			"A": "U"
			}
	code = ""

	if len(dna) == 1:
		return rna[dna]
	else:
		for i in dna:
			code += rna[i]
	return code
