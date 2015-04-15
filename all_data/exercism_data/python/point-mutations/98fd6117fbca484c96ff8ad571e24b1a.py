class DNA:
	def __init__(self, s):
		self.s = s
		
	def hamming_distance(self, s2):
		return len([e for e in zip(self.s, s2) if e[0] != e[1]])
