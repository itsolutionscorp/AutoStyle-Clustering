def to_rna(dna):

	dna_and_rna = {'G': 'C', 'C':'G', 'T':'A', 'A':'U'}
	rna = ''

	for nucleotide in dna:
		rna += dna_and_rna[nucleotide]
	return rna
