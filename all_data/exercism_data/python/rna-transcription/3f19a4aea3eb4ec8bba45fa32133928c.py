class DNA:
	def __init__ (self, sequence):
		self.sequence = sequence
		self.DNA2RNACOMP = {'A':'U', 'C':'G', 'G':'C', 'T':'A'}
	
	def to_rna(self):
		rna = ""
		for a in self.sequence :
			rna += self.DNA2RNACOMP[a]
		return rna
	
