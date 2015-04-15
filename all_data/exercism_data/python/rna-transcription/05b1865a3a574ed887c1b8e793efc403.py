def to_rna(seq):

	keyRNA = {'A': 'U', 'T': 'A', 'C': 'G', 'G': 'C'}
	rna = ""
	for nuc in seq:
		rna += keyRNA[nuc]
	return rna
