from string import maketrans 

def to_rna(dnaStrand):
	
	#translation table for RNA complement from a DNA strand
	trantable = maketrans("GCTA", "CGAU")

	#translate the DNA strand per the translation table
	rnaStrand = dnaStrand.translate(trantable)

	return rnaStrand
