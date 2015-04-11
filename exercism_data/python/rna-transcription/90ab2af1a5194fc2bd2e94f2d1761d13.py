class DNA(object):
	""" Class representing DNA strand """
	def __init__(self, strand):
		self.strand = strand

	def to_rna(self):
		""" Converts current DNA strand to respective RNA strand """
		return self.strand.replace('T', 'U')
