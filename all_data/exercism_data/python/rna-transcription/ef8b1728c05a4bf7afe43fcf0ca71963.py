def to_rna(x):
	toReturn = ''
	dict = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
	for i in x:
		toReturn += dict[i]
	return toReturn
