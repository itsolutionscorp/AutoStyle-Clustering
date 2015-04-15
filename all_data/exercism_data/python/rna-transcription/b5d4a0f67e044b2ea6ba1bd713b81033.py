class DNA(object):
	TO_RNA = {
		'G': 'C',
		'C': 'G',
		'T': 'A',
		'A': 'U'
	}
	def __init__(self, strand):
		self.strand = strand

	def to_rna(self):
		return ''.join([DNA.TO_RNA[c] for c in self.strand])
