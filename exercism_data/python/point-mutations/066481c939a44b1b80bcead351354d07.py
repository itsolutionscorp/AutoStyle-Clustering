class DNA:
	def __init__(self, seq):
		self.seq = seq
	def hamming_distance(self, t_seq):
		diff = 0
		for c1, c2 in zip(self.seq, t_seq):
			if c1 != c2:
				diff = diff + 1
		return diff
