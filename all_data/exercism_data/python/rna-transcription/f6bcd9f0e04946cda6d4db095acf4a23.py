def to_rna(dna_strand):
	rna_strand = ''

	for dna_nuc in dna_strand:
		
		if dna_nuc == 'G':
			rna_strand += 'C'

		elif dna_nuc == 'C':
			rna_strand += 'G'

		elif dna_nuc == 'T':
			rna_strand += 'A'

		elif dna_nuc == 'A':
			rna_strand += 'U'

		else:
			rna_strand += ''

	return rna_strand
