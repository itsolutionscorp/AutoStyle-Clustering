class DNA:
	def __init__(self, sequence):
		self.sequence = sequence or ""

	# A base-paired DNA sequence:
	#    ATCGATTGAGCTCTAGCG
	#    TAGCTAACTCGAGATCGC
	#
	# The corresponding RNA sequence:
	#    AUCGAUUGAGCUCUAGCG
	#    UAGCUAACUCGAGAUCGC
	#
	# ref: https://en.wikipedia.org/wiki/Base_pair
	def to_rna(self):
		return self.sequence.replace("T", "U")
