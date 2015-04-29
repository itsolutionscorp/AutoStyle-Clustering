from string import maketrans

def to_rna(nucleotide):
	complements = maketrans('GCTA', 'CGAU')
	nuc = nucleotide.translate(complements)

	return nuc 

	
