def to_rna(s):
	dic = {'G':'C', 'T':'A', 'A':'U', 'C':'G'}
	for i, j in dic.iteritems():
		s = s.replace(i, j)
	return s
