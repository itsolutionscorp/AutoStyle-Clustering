import string
table = string.maketrans('CGTA', 'GCAU')

def to_rna(sequence):
	return sequence.translate(table)
