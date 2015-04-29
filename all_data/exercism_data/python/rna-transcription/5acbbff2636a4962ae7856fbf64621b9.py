from string import maketrans
def to_rna(dna):
	trans_table = maketrans('GCTA', 'CGAU')
	return dna.upper().translate(trans_table)
