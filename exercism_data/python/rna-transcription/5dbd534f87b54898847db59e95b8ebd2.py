def to_rna(string):
	dict = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
	retlist = []
	for i in string:
		retlist.append(dict[i])
	retstring = ''.join(retlist)
	return retstring


