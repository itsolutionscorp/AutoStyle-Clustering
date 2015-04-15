class Word:
	tmp = [("AEIOULNRST", 1), ("DG", 2), ("BCMP", 3), ("FHVXY", 4), ("K", 5), ("JX", 8), ("QZ", 10)]
	VALUES = {}
	[VALUES.update(dict.fromkeys(s, v)) for s, v in tmp]
	
	def __init__(self, word):
		self.word = word
	
	def score(self):
		return sum([self.VALUES.get(l.upper(), 0) for l in self.word])
