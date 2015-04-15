def to_rna(dna):
	codes = {
		"G": "C",
		"C": "G",
		"T": "A",
		"A": "U"
	}

	rna = []

	for i in dna:
		rna.append(codes[i])

	return ''.join(rna)
