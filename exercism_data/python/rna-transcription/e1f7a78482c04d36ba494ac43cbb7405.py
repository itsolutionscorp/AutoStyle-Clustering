class DNA(object):
	def __init__(self,seq):
		self.seq=seq
	def to_rna(self):
		trans= {'G':'C','C':'G','T':'A','A':'U'}
		rna = ''.join([trans[s] for s in self.seq])
		return rna
