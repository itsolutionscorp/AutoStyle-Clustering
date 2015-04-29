import string
from collections import Counter

class Phrase(str):
	def word_count(self):
		return Counter(self.translate(None, string.punctuation).lower().split())
