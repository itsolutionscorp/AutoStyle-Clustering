def convert_dna_nuc_to_rna(dna_nuc):

	rna_nuc = ''

	
	if dna_nuc == 'G':
		rna_nuc = 'C'

	elif dna_nuc == 'C':
		rna_nuc = 'G'

	elif dna_nuc == 'T':
		rna_nuc = 'A'

	elif dna_nuc == 'A':
		rna_nuc = 'U'

	else:
		rna_nuc = ''

	return rna_nuc

def to_rna(dna_strand):
	rna_strand = ''

	for dna_nuc in list(dna_strand):
		rna_strand+=str(convert_dna_nuc_to_rna(dna_nuc))

	return rna_strand
