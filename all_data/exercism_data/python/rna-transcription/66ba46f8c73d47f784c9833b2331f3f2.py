from string import maketrans   # Required to call maketrans function.

class DNA:
	def __init__(self, dnaStrand):
		self.dna = dnaStrand

	def to_rna(self):
		dnaStrand = "GCTA"
		rnaCompliment = "CGAU"
		translationTable = maketrans(dnaStrand, rnaCompliment)

		return self.dna.translate(translationTable)
