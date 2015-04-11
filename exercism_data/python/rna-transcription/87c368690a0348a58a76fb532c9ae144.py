class DNA:

	#Creates a dictionary and a mapping to translate strings with
	mapping_dict = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
	mapping = str.maketrans(mapping_dict)

	#Creates instance variable containing the dna string to be passed
	def __init__(self, string):
		self.dna = string

	#uses the builtin string translate() method to map the string
	def to_rna(self):
		return self.dna.translate(self.mapping)
