# dna.py
"""
* `G` -> `C`
* `C` -> `G`
* `T` -> `A`
* `A` -> `U`
"""
class DNA:
	def __init__(self,v):
		""" Store incoming string """
		self.v = v	# assuming valid string
		self.d = dict(zip('GCTA','CGAU'))

	def to_rna(self):
		""" Return transcription """
		return "".join([self.d[x] for x in self.v])
