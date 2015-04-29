def to_rna(seq):
	rna = ""

	for i in seq:
		if i == 'G':
			rna += ('C')
		elif i == 'C':
			rna += ('G')
		elif i == 'T':
			rna += ('A')
		elif i == 'A':
			rna += ('U')
		else:
		 return "Input Error!"

	return rna
