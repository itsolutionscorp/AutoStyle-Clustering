class DNA(object):
	def __init__(self, strand):
		self.strand = strand

	def hamming_distance(self, other_strand):
		paired = zip(self.strand, other_strand)
		return sum(1 for pair in paired if pair[0] != pair[1])
