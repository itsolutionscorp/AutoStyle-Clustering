import string

class Phrase:
	def __init__(self, w):
		lw = w.translate(None, string.punctuation).lower().split()
		self.d = {}
		for word in lw:
			if word not in self.d:
				self.d[word] = 1
			else:
				self.d[word] += 1
	
	def word_count(self):
		return self.d
