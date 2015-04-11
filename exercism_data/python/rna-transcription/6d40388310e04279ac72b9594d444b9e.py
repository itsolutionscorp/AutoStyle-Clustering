def to_rna(dna):
	
	rna = ''
		
	for nucleotide in dna:
		if nucleotide == 'A':
			rna += 'U'
		elif nucleotide == 'T':
			rna += 'A'
		elif nucleotide == 'G':
			rna += 'C'
		elif nucleotide == 'C':
			rna += 'G'
		else:
			oops = 'Is this bad?'
			return oops

	return rna

	
