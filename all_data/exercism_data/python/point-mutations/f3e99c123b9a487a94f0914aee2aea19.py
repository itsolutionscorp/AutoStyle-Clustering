class DNA(object):
	def __init__(self, strand):
		self.strand = strand

	def hamming_distance(self, other_strand):
		return sum(int(a != b) for a, b in zip(self.strand, other_strand))
