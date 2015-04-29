class DNA:
	def __init__(self, dna_seq):
		self._dna_seq = dna_seq

	def to_rna(self):
		rna_seq = self._dna_seq.replace('T', 'U')
		return rna_seq
