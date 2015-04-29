class Anagram:
	def __init__(self, s):
		self.s = s
		
	def match(self, l):
		return [e for e in l if self.s != e and sorted(e[:].lower()) == sorted(self.s.lower())]
