import string

class DNA(object):
	def __init__ (self, string):
		self.string = string


	def to_rna(self):
		dna_ch = "T"
		rna_ch = "U"
		result = string.replace(self.string, dna_ch, rna_ch)
		return result 
