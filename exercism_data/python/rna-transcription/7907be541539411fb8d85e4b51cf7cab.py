class DNA(object):
	def __init__(self, strand):
		self.strand = strand

	def to_rna(self):
		rna = ""
		for letter in self.strand:
			if letter != 'T':
				rna += letter
			else:
				rna += 'U'
		return rna
