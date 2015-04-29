def to_rna(d):
	rna = []
	for c in d:
		if c == 'G':
			rna.append('C')
		elif c == 'C':
			rna.append('G')
		elif c == 'T':
			rna.append('A')
		elif c == 'A':
			rna.append('U')
		else:
			return "THAT AIN'T DNA, FOOL!"
			
	return ''.join(rna)
