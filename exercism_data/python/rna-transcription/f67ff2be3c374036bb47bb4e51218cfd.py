class DNA:
	def __init__(self, dna):
		self.dna = dna
	def to_rna(self):
		return self.dna.translate(self.dna.maketrans('GCTA', 'CGAU'))
