from string import maketrans

class DNA(object):
	def __init__(self, sequence):
		self.sequence = sequence
		
	def to_rna(self):
		return self.sequence.translate(maketrans('T','U'))
