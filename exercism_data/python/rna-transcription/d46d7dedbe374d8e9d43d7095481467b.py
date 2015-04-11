class DNA:
	def __init__(self, s):
		self.s = s 
	def to_rna(self):
		rna = ""
		for c in self.s:
			if c == 'T':
				rna += 'U'
			else:
				rna += c
		return rna
