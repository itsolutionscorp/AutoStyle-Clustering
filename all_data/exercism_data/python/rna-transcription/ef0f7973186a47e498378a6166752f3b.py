from string import maketrans 

DNA = 'GCTA'
RNA = 'CGAU'
translations = maketrans(DNA, RNA)

def to_rna(dna_strand):
	return dna_strand.translate(translations)
