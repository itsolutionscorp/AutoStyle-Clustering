""" Class representing DNA strand """
class DNA(object):
	def __init__(self, strand):
		self.strand = strand

	""" Converts current DNA strand to respective RNA strand """
	def to_rna(self):
		return self.strand.replace('T', 'U')
