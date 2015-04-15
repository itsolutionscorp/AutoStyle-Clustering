def convert_dna_to_rna(strand):
	def convertChar(char):
		DNA = "GCTA"
		RNA = "CGAU"
		
		if char in DNA:
			return RNA[DNA.index(char)]
		
		return char

	return ''.join(map(convertChar, strand))

class DNA:
	def __init__(self, DNAstrand):
		self.DNAstrand = DNAstrand
		self.RNAstrand = convert_dna_to_rna(self.DNAstrand)
		
	def to_rna(self):
		return self.RNAstrand
