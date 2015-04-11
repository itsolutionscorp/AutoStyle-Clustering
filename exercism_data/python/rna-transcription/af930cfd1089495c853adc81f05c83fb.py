class DNA:

	def __init__(self, ntide_string):
		self.ntide_string = ntide_string


	def to_rna(self):
		map_dict = {'A':'U','C':'G','G':'C','T':'A'}
		my_list = [map_dict[x] for x in self.ntide_string]
		return ''.join(my_list)
