class DNA(object):
	def __init__(self, dnaString):
		self.dna = dnaString

	def to_rna(self):
		dnaString = self.dna
		rnaString = ""
		for x in dnaString:
			if x == "G":
				rnaString += "C"
			if x == "C":
				rnaString += "G"
			if x == "T":
				rnaString += "A"
			if x == "A":
				rnaString += "U"
		return rnaString
