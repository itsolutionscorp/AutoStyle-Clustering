import string

class DNA_class:
	def __init__(self, s):
		self.s = s
		self.tr = string.maketrans("GCTA", "CGAU")

	def to_rna(self):
		return self.s.translate(self.tr)
		
def DNA(s):
	return DNA_class(s)
