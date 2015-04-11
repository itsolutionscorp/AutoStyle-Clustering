def to_rna(strand):
	rna = ""
	dna_to_rna = dict(zip("GCTA", "CGAU"))
	for s in strand:
		rna += dna_to_rna[s]
	return rna
