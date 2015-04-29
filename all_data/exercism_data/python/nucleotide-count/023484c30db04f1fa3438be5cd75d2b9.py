class DNA:
	def __init__(self, DNAString):
		self.DNAString = DNAString
		
	def nucleotide_counts(self):
		counts = {'A':0, 'T':0, 'C':0, 'G':0}
		
		for n in self.DNAString:
			if n in counts:
				counts[n] += 1

		return counts
		
	def count(self, nucleotide):
		if (nucleotide == 'U'): return 0
		
		if not (nucleotide in "ACGT"):
			raise ValueError(nucleotide + ' is not a nucleotide.')
		
		return self.nucleotide_counts()[nucleotide]
