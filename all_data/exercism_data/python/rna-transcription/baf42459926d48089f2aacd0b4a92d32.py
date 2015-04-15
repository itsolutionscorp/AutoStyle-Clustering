DNA_to_RNA = { 
	'G' : 'C',
	'C' : 'G',
	'T' : 'A',
	'A' : 'U' }

def to_rna(strand):
	return ''.join([DNA_to_RNA[x.upper()] for x in strand])
