class DNA:
	def __init__(self, DNAString):
		self.DNAString = DNAString
		
	def hamming_distance(self, otherDNA):
		length = min(len(self.DNAString), len(otherDNA))
		
		hamming = 0
		
		for i in range(length):
			orig = self.DNAString[i]
			other = otherDNA[i]
			
			if (orig != other):
				hamming += 1
				
		return hamming
