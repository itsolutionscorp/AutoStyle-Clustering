def to_rna(DNA):
	dict = {'G':'C','C':'G','T':'A','A':'U'}
	rna = "".join([dict[x] for x in list(DNA)])
	return rna
