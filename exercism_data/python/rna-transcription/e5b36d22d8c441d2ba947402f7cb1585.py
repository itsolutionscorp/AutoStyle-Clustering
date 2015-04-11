def to_rna(dna):
	rna = ''
	for char in dna:
		if ('C' == char):
			traducao = 'G'
		elif ('G' == char):
			traducao = 'C'
		elif ('T' == char):
			traducao = 'A'
		elif ('A' == char):
			traducao = 'U'
		rna = rna + traducao

	return rna
