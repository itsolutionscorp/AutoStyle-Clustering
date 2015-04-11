def to_rna(nucleotide):
	dna = 'GCTA'
	rna = 'CGAU'
	complement = str.maketrans(dna, rna)
	return nucleotide.translate(complement)
