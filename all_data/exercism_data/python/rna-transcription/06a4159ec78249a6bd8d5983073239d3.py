class DNA:
	def __init__(self, strand):
		self.strand = strand; #DNA's strand in a string format

	def to_rna(self):
		return self.strand.replace('T', 'U');
