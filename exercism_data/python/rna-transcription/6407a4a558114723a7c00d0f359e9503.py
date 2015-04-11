def to_rna(sequence):
	trns = []
	for nuc in sequence:
		if nuc == 'A':
			trns.append('U')
		if nuc == 'G':
			trns.append('C')
		if nuc == 'T':
			trns.append('A')
		if nuc == 'C':
			trns.append('G')
	return ''.join(trns)
