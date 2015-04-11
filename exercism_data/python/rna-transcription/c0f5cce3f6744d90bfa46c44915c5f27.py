def to_rna(strand):
	dnad = {'G': 'C', 'C':'G', 'T':'A', 'A':'U'}
	trans = ''
	for i in strand:
		trans += dnad[i]
	return trans
