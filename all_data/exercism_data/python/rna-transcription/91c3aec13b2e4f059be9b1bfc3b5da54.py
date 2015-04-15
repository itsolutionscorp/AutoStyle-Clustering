class DNA():
	def __init__(self, dna):
		self.dna = dna
	def to_rna(self):
		rna = ''
		for nucleotide in self.dna:
			if nucleotide == 'C':
				rna += 'C'
			elif nucleotide == 'G':
				rna += 'G'
			elif nucleotide == 'A':
				rna += 'A'
			elif nucleotide == 'T':
				rna += 'U'
			else:
				return 'Not a nucleotide'
		return rna
