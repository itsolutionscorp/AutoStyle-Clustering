def to_rna(dna):
	rna = []

	for nucleotide in dna:
		if nucleotide == 'G':
			rna.append('C')
		elif nucleotide == 'C':
			rna.append('G')
		elif nucleotide == 'T':
			rna.append('A')
		else:
			rna.append('U')

	return ''.join(rna)
