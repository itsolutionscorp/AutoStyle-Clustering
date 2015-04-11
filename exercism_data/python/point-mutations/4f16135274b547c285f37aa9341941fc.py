class DNA(object):
	def __init__(self, strand):
		self.strand = strand
	
	def hamming_distance(self, strand):
		return 0 if self.strand == strand else sum(n1 != n2 for n1, n2 in zip(self.strand, strand))
