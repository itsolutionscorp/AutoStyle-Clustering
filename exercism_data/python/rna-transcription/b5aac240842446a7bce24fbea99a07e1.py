def to_rna(dna):
	rna = []
	for n in dna:
		if n == 'G':
			rna.append('C')
		if n == 'C':
			rna.append('G')
		if n == "T":
			rna.append('A')
		if n == "A":
			rna.append('U')
	return ''.join(rna)
