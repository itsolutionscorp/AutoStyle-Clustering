import string
import re

class Phrase:
	def __init__(self, word):
		self.word = word.translate(None, string.punctuation)
		self.word = self.word.lower()

	def word_count(self):

		word_counts = {}
		for w in re.split('\W+', self.word):
			word_counts.setdefault(w, 0)
			word_counts[w] = word_counts[w] + 1
		return word_counts
