def to_rna(strand):
	output = ""
	for nucleotide in strand:
		if nucleotide == 'G':
			output += 'C'
		elif nucleotide == 'C':
			output += 'G'
		elif nucleotide == 'T':
			output += 'A'
		elif nucleotide == 'A':
			output += 'U'
	return output	
