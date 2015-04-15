class DNA(object):
	def __init__(self, dna):
		self._dna = dna

	def to_rna(self):
		rna = ["U" if i == "T" else i for i in self._dna]
		return "".join(rna)
