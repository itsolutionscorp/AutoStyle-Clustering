class DNA():

	def __init__(self, dna):
		self.dna = dna

	def to_rna(self):
		result = ""
		for c in self.dna:
			result += self.replace(c)
		return result

	def replace(self, character):
		if character == "G":
			return "C"
		elif character == "C":
			return "G"
		elif character == "T":
			return "A"
		elif character == "A":
			return "U"
