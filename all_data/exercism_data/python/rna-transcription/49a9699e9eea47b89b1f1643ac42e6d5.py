class DNA:
	def __init__(self, repr):
		self.dna = repr

	def to_rna(self):
		return self.dna.replace('T', 'U')
