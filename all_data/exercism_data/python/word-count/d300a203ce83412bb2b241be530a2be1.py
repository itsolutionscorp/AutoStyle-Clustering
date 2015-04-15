import string
from collections import Counter

class Phrase:
	def __init__(self, w):
		self.d = Counter(w.translate(None, string.punctuation).lower().split())
	
	def word_count(self):
		return self.d
