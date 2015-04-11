class DNA():
	def __init__(self, dna_sequence):
		self.dna_sequence = dna_sequence

	def to_rna(self):
		rna_sequence = ""

		# Loop through self.dna_sequence, and concatenate 
		# a corresponding rna_sequence string
		for nucleotide in self.dna_sequence:
			if nucleotide == "G":
				rna_sequence += "C"

			elif nucleotide == "C":
				rna_sequence += "G"

			elif nucleotide == "T":
				rna_sequence += "A"

			elif nucleotide == "A":
				rna_sequence += "U"
		return rna_sequence
