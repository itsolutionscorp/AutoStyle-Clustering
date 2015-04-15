from string import maketrans 

dna = 'GCTA'
rna = 'CGAU'
conversion = maketrans(dna,rna)

def to_rna(dna_strand):
	
	return dna_strand.translate(conversion)
