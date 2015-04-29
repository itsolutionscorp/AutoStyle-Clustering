#DNA to RNA Converstion

def to_rna(dna): 
	rna = [x for x in dna]

	for i, nuc in enumerate(rna):
		if nuc == 'C':
			rna[i] = 'G'
		elif nuc == 'G':
			rna[i] = 'C'
		elif nuc == 'A':
			rna[i] = 'U'
		elif nuc == 'T':
			rna[i] = 'A'

	return ''.join(rna)


