def to_rna(strand):
	mapper = {'A':'U', 'C':'G', 'G':'C', 'T':'A'}
	result = [mapper[n] for n in strand]
	return ''.join(result)
