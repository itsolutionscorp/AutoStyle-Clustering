from string import maketrans 

def to_rna(dna_strand):
	dna = 'GCTA'
	rna = 'CGAU'
	conversion = maketrans(dna,rna)
	
	return dna_strand.translate(conversion)
