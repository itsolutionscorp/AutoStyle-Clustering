def to_rna(dna):
	rna = []
	for n in dna:
		if n == 'G':
			rna.append('C')
		elif n == 'C':
			rna.append('G')
		elif n == "T":
			rna.append('A')
		elif n == "A":
			rna.append('U')
	return ''.join(rna)
