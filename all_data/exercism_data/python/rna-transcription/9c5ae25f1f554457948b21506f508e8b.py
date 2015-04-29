def to_rna(dna):

	dna_and_rna = {'G': 'C', 'C':'G', 'T':'A', 'A':'U'}
	rna = ''

	for i in range(0, len(dna)):
		rna = rna + dna_and_rna[ dna[i] ]
	return rna
