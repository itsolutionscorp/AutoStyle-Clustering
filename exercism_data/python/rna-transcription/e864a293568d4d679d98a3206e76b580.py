def to_rna(dna):
	zuruck = ''
	for nucleotide in dna:
		if nucleotide == 'G':
			zuruck += 'C'
		if nucleotide == 'C':
			zuruck += 'G'
		if nucleotide == 'T':
			zuruck+='A'
		if nucleotide== 'A':
			zuruck += 'U'
			
	return zuruck
      
