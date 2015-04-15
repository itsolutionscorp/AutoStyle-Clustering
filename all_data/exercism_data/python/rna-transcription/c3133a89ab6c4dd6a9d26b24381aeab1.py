import string

class DNA:
	dna_string = ''
	
	def __init__(self, dna_string):
		self.dna_string = dna_string
	
	def to_rna(self):
		return string.replace(self.dna_string, 'T', 'U')
