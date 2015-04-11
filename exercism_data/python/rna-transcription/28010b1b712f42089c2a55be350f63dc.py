def to_rna(x):
	trans_dict = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
	return ''.join([trans_dict[i] for i in x])

