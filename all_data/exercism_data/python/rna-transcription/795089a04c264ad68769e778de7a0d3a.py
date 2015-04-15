class DNA():

	def __init__(self, dna_string):
		self.translation = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}		# Define the translation map
		self.dna_string = dna_string
		

	def to_rna(self):
		""" Translates the DNA string to the corresponding transcribed RNA string """
		rna_string = ""

		for dna_nucleotide in self.dna_string:
			rna_nucleotide = self.translation[dna_nucleotide]
			rna_string += rna_nucleotide

		return rna_string
