class DNA:
	def __init__(self, dna):
		self.dna = dna
		self.valid_nucleotides = ["A", "C", "G", "T", "U"]
		
	def count(self, nucleotide):
		if not nucleotide in self.valid_nucleotides:
			raise ValueError(nucleotide + ' is not a nucleotide.')
		return self.dna.count(nucleotide)
		
	def nucleotide_counts(self):
		return { n: self.count(n) for n in self.valid_nucleotides if n is not "U" }
