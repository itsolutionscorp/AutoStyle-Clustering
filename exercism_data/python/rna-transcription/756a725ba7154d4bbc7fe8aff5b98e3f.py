DNA_RNA_MAP = {
	'G': 'C',
	'C': 'G',
	'T': 'A',
	'A': 'U'
}

def to_rna(seq):
	return "".join([DNA_RNA_MAP[s] for s in seq])
