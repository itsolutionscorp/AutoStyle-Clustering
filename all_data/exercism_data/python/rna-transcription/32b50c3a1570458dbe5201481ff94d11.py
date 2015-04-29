class DNA:
	
	def __init__(self, dna):
		self.dna = dna

	def to_rna(self):
		result = ''
		result += letter for letter in self.dna if letter != 'T' else 'U'
		return result
