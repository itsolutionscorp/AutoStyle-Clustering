class DNA():
	
	def to_rna(self):
		rna = ''
		for i in range(0, len(self.dna_str)):
			if self.dna_str[i] == 'C':
				rna = rna + 'C'
			elif self.dna_str[i] == 'G':
				rna = rna + 'G'
			elif self.dna_str[i] == 'A':
				rna = rna + 'A'
			elif self.dna_str[i] == 'T':
				rna = rna + 'U'
		return rna
		
	def __init__(self, dna):
		self.dna_str = dna
