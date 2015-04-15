def to_rna(dna): 
	l = []
	for i in dna:
		if i == 'G': l.append('C')
		elif i == 'C': l.append('G')
		elif i == 'T': l.append('A')
		elif i == 'A': l.append('U')
		rna = ''.join(l)
	return (rna)
