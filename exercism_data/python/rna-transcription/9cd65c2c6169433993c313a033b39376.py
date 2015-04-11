from string import maketrans
class DNA():
	def __init__(self, dna):
		self.dna = dna
	
	def to_rna(self):
		transmap = maketrans('GCTA','CGAU')
		return self.dna.translate(transmap) 

	
