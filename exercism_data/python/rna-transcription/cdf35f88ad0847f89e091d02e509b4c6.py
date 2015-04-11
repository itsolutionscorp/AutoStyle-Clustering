from string import maketrans 

def to_rna(DNAstrand):
	
	#translation table for RNA complement from a DNA strand
	dnaNucleotides = "GCTA"
	rnaNucleotides = "CGAU"
	trantable = maketrans(dnaNucleotides, rnaNucleotides)

	#translate the DNA strand per the translation table
	RNAstrand = DNAstrand.translate(trantable)

	return RNAstrand
