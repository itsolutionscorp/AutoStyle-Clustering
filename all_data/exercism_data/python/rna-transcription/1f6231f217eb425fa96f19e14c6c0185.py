class DNA:
	def __init__(self, dna):
		self.dna = dna
	
	def to_rna(self):
		rna = ''

		for base in self.dna:
			if base == 'T':
				rna += 'U'
			else:
				rna += base

		return rna
