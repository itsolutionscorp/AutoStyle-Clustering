class DNA(object):
	def __init__(self, dna):
		self._dna = dna

	def to_rna(self):
		return self._dna.replace("T", "U")
