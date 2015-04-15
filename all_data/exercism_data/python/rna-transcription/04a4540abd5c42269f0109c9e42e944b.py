from string import maketrans

def to_rna(strand):
	trans_table = maketrans('GCTA', 'CGAU')
	return strand.translate(trans_table)
