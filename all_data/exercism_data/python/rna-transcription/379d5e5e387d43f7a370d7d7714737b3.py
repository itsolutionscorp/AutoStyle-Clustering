def to_rna(dna):
	lst = []
	for x in dna:
		if x=='G':
			lst.append('C')
		elif x=='C':
			lst.append('G')
		elif x=='A':
			lst.append('U')
		else:
			lst.append('A')

	return "".join(lst)
