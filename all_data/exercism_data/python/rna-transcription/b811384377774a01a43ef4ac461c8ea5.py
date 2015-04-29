from string import maketrans

def to_rna(dna_sequence):
	rna_complement = maketrans('ATCG', 'UAGC')

	return dna_sequence.translate(rna_complement)
