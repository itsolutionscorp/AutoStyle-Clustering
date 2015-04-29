class DNA(object):
	def __init__(self, sequence):
		super(DNA, self).__init__()
		self.sequence = sequence

	def to_rna(self):
		return self.sequence.replace('T', 'U')
		
