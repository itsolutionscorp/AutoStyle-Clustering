from string import maketrans
def to_rna(DNA):
	# Order is important in these 2 strings
	DNAorig='GCTA'
	RNAorig='CGAU'

	# Make a translation table for each character
	DNAtoRNAtab=maketrans(DNAorig,RNAorig)

	return DNA.translate(DNAtoRNAtab) 
