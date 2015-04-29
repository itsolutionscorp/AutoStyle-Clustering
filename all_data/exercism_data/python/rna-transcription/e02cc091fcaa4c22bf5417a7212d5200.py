# takes strand of dna, translates it to rna
def to_rna(dna): 
	import string

	dna = dna.upper()
	
	# use translation table to change dna to rna
	rna_trans = string.maketrans("GCTA", "CGAU")
	rna = string.translate(dna, rna_trans)
	return rna
	
