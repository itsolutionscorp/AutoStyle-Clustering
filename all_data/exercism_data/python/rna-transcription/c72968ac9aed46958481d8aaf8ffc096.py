def to_rna(seq):
	"""Returns RNA string representation of seq"""
	rnaEncodingENCODE = { 'G': 'C', 'C': 'G','T': 'A', 'A': 'U'}
	return "".join(rnaEncoding[x] for x in seq)

def to_rna1(seq):
	rnaEncoding = { 'G': 'C', 'C': 'G','T': 'A', 'A': 'U'}
	return "".join(map(lambda x : rnaEncoding[x], seq))

					
	
