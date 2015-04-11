class DNA:
	def __init__(self,dnaPassed):
		self.rnaMap={'G':'C','C':'G','T':'A','A':'U'}
		self.dnaToMap=dnaPassed

	def to_rna(self):
		returnBuffer = ""
		for ch in self.dnaToMap:
			returnBuffer += (self.rnaMap[ch])
		return returnBuffer
		
	def DNA(dnaPassed):
		return rnaMap[dnaPassed];
