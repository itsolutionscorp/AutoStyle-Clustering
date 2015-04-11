class DNA(object):
	def __init__(self, dna_sequence):
		self.dna_sequence = dna_sequence

	def to_rna(self):
		return self.dna_sequence.replace('T', 'U')
