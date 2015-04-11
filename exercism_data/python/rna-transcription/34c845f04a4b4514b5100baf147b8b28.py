def to_rna(seq):
	basepairs = {'G':'C','C':'G','T':'A','A':'U'}
	rna = ''
	for x in seq.upper():
		rna += basepairs[x]
	return rna
	#realized last submission would not maintain order
