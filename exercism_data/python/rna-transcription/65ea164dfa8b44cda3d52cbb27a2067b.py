def to_rna(str):
	dict = {'G':'C', 'C':'G','T':'A', 'A':'U'}
	slist = list(str)
	for i in range(len(slist)):
		slist[i] = dict[slist[i]]
	return ''.join(slist)
