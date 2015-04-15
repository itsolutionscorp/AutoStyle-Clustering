class DNA(object):
	__mapping = {'G':'C','C':'G','T':'A','A':'U'}

	def __init__(self,nucleotides):
		self.nucleotides = nucleotides
	def to_rna(self):
		return ''.join(map(lambda x:self.__mapping[x], self.nucleotides))
