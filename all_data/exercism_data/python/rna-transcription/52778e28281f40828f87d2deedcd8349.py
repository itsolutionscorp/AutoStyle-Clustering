def to_rna(string):
	transcode = {'C':'G','G':'C','T':'A','A':'U'}
	return ''.join([transcode[i] for i in string])
