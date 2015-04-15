class DNA:
	def __init__(self,dnaPassed):
		self.rnaMap={'G':'C','C':'G','T':'A','A':'U'}
		self.dnaToMap=dnaPassed

	def to_rna(self):
		return "".join([self.rnaMap[x] for x in self.dnaToMap])
		
	def DNA(dnaPassed):
		return rnaMap[dnaPassed];
