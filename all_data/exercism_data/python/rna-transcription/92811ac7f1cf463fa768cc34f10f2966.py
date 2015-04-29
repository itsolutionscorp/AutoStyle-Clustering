class DNA:
	
	def __init__(self, dna):
		self.dna = dna

	def to_rna(self):
		result = ''
		for letter in self.dna:
			result += letter if letter != 'T' else 'U'
		return result
