from string import maketrans

def to_rna(dna_strand):

	dna_nucs = 'GCTA'
	rna_nucs = 'CGAU'
	trans_table = maketrans(dna_nucs, rna_nucs)

	return dna_strand.translate(trans_table)
