import string

class DNA:
	_dnaNucleotides = "GCTA"
	_rnaNucleotides = "CGAU"
	_translation = string.maketrans(_dnaNucleotides, _rnaNucleotides)

	def __init__(self, dna):
		self.dna = dna

	def to_rna(self):
		return self.dna.translate(self._translation)
