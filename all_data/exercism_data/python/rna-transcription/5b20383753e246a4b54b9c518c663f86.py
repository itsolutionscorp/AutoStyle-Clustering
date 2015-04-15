def to_rna(DNAstrand):
	
	from string import maketrans 

	dna = "GCTA"
	rna = "CGAU"
	trantable = maketrans(dna, rna)

	return DNAstrand.translate(trantable);
