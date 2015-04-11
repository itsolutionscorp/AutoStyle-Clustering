class DNA:
	
	def __init__(self, dna):
		self.dna = dna

	def to_rna(self):
		from string import maketrans
		tran = maketrans('ACGT', 'ACGU')
		return self.dna.translate(tran)
