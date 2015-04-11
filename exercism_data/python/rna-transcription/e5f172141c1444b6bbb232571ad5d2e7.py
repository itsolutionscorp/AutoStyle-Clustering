class DNA():

	def __init__(self, dna_string):
		self._translation = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}		# Define the translation map
		self._dna_string = dna_string


	def to_rna(self):
		""" Translates the DNA string to the corresponding transcribed RNA string """
		rna_string = []

		for dna_nucleotide in self._dna_string:
			rna_nucleotide = self._translation[dna_nucleotide]
			rna_string.append(rna_nucleotide)

		return "".join(rna_string)
