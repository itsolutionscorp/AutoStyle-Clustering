#convert a string representing a DNA sequence to RNA

def to_rna(dna_strand):
	rna_strand = ''
	for nucleotide in dna_strand:
		if nucleotide == 'A':
			rna_strand += 'U'
		elif nucleotide == 'C':
			rna_strand += 'G'
		elif nucleotide == 'G':
			rna_strand += 'C'
		elif nucleotide == 'T':
			rna_strand += 'A'
	return rna_strand
