from string import maketrans 

def to_rna(dna_strand):
	
	#make translation table for RNA complement from a DNA strand
	trantable = maketrans("GCTA", "CGAU")

	#translate the DNA strand with the translation table
	rna_strand = dna_strand.translate(trantable)

	return rna_strand
