def to_rna(sequence):
	rna = ''
	for base in sequence:
		if base == 'C':
			rna += 'G'
			continue
		elif base == 'G':
			rna += 'C'
			continue
		elif base == 'T':
			rna += 'A'
			continue
		elif base == 'A':
			rna += 'U'
			continue
	return rna
