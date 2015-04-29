class DNA(object):
	def __init__(self, sequence):
		self._dna = sequence or ""
		self._rna = None

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
		if not self._rna:
			self._rna = self._dna.replace("T", "U")
		return self._rna
