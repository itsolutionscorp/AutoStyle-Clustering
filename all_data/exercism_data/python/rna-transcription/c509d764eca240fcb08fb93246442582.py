def to_rna(dna):
	dna_2_rna = {'G': 'C', 'C': 'G', 'A': 'U', 'T': 'A'}
	rna = ''
	for nucleo in dna:
		rna += dna_2_rna[nucleo]
	return rna
