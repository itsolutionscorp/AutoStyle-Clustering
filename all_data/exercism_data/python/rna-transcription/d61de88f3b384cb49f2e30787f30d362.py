def to_rna(dna):
	dict = {'G':'C', 'C':'G', 'T': 'A', 'A':'U'}
	rna = ''
	for i in range(0,len(dna)):
		rna += dict[dna[i]]
	return rna
