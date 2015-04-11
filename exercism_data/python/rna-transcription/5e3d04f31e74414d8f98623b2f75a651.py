def to_rna(dna_strand):
	rna_strand = ''
	for nucleotide in dna_strand:
		rna_strand += get_complement(nucleotide)
	return rna_strand

def get_complement(nucleotide):
	if nucleotide == 'G':
		return 'C'
	elif nucleotide == 'C':
		return 'G'
	elif nucleotide == 'T':
		return 'A'
	elif nucleotide == 'A':
		return 'U'
	else:
		return ''
