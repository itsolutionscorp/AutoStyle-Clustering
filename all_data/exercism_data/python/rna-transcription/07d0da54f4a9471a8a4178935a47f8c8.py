def to_rna(dna_sample):
	sequence = {'G': 'C','C': 'G', 'T':'A', 'A': 'U'}
	rna = ''
	for i in dna_sample:
		rna += sequence[i]
	return rna
