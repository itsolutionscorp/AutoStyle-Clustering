class DNA(object):
	def __init__(self, strand):
		self.strand = strand
	
	def hamming_distance(self, strand):
		ham_dist = 0
		if self.strand == strand:
			return ham_dist
		check = len(strand) if len(self.strand) > len(strand) else len(self.strand)
		for i in range(check):
			if self.strand[i] != strand[i]:
				ham_dist += 1
		return ham_dist
