class DNA:
	def __init__(self, value):
		self.value = value

	def to_rna(self):
		return self.value.replace('T', 'U')
