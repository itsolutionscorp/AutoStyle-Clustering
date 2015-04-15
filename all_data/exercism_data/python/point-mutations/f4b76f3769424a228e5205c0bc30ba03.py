class DNA:
	def __init__(self, strand):
		self.strand = strand
		
	def hamming_distance(self, strand2):
		return len([pair for pair in zip(self.strand, strand2) if pair[0] != pair[1]])
