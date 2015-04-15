def to_rna(seq):
	rnaEncoding = { 'G': 'C', 'C': 'G','T': 'A', 'A': 'U'}
	return "".join(map(lambda x : rnaEncoding[x], seq))				
