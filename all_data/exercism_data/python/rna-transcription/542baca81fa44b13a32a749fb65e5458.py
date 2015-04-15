from string import maketrans
# Order is important in these 2 strings
DNAorig='GCTA'
RNAorig='CGAU'

# Make a translation table for each character
DNAtoRNAtab=maketrans(DNAorig,RNAorig)

def to_rna(DNA):

	return DNA.translate(DNAtoRNAtab) 
