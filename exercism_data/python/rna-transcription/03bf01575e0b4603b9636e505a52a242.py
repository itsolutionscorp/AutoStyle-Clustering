def to_rna(sequence):
	rna = ""

	for i in range(len(sequence)):
		nucleotide = sequence[i]

		if nucleotide == 'G':
			rna = rna + 'C'
		elif nucleotide == 'C':
			rna = rna + 'G'
		elif nucleotide == 'A':
			rna = rna + 'U'
		elif nucleotide == 'T':
			rna = rna + 'A'

	return rna
