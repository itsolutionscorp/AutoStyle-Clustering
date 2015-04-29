import re

class Phrase:
	def __init__(self, str):
		self.str = str

	def word_count(self):
		words = re.sub('[^\w]', ' ', self.str).lower().split()
		hash = {}
		for w in words:
			if w in hash:
				hash[w] = hash[w] + 1
			else:
				hash[w] = 1
		return hash
