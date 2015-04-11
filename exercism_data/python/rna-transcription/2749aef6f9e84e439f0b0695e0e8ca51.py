def to_rna(dna_sequence):
	result = ''
	for nucleotide in dna_sequence:
		if nucleotide == 'G':
			result += 'C'
		elif nucleotide == 'C':
			result += 'G'
		elif nucleotide == 'T':
			result+= 'A'
		elif nucleotide == 'A':
			result += 'U'
	return result
