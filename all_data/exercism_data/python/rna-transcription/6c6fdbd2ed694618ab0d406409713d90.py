class DNA(object):
	def __init__(self, sequence):
		self.dna = sequence or ""
		# Calculate rna only once.
		self.rna = self.dna.replace("T", "U")

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
		return self.rna
