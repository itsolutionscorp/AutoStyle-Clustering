class DNA:
		N = {
			"G" : "C",
			"C" : "G",
			"T" : "A",
			"A" : "U",
		}

		def __init__(self, s):
			self.s = s

		def to_rna(self):
			output = ""
			for i in self.s:
				output += self.N[i]
			return output
