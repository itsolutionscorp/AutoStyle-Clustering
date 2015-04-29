from string import maketrans
def to_rna(dna):
	#Takes an input of a DNA string and returns its complimentary
	#rna string
    return dna.translate(maketrans('GCTA', 'CGAU'))
