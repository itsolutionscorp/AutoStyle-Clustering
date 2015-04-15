class DNA(object):
	def __init__(self, sequence):
		self.dna = sequence or ""
		self.rna = None

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
		# Calculate rna only once.
		if not self.rna:
			self.rna = self.dna.replace("T", "U")
		return self.rna
