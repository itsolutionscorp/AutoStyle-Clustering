from string import maketrans 

def to_rna(dna_strand):

	DNA = 'GCTA'
	RNA = 'CGAU'
	translations = maketrans(DNA, RNA)
	return dna_strand.translate(translations)
