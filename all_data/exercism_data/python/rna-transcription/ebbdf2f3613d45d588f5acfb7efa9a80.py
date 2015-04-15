from string import maketrans

dna2rna_table = maketrans('GCTA', 'CGAU')

class DNA (object):
	def __init__(self, s):
		self.s = s
		
	def to_rna(self):
		return self.s.translate(dna2rna_table)
