

def to_rna(sequence):
	rna = ''
	for nuc in sequence:
		if nuc == 'A':
			rna += ('U')
		elif nuc == 'T':
			rna += ('A')
		elif nuc == 'G':
			rna += ('C')
		elif nuc == 'C':
			rna += ('G')
	return rna
