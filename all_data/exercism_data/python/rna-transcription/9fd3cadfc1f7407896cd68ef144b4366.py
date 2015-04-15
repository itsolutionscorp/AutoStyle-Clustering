class DNA:
	
	def __init__(self, dna):
		self.dna = dna

	def to_rna(self):
		# in Python 2 - from string import maketrans
		tran = str.maketrans('ACGT', 'ACGU')
		return self.dna.translate(tran)
