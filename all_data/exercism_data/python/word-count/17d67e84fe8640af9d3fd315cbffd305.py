import collections
import string

class Phrase(object):
	def __init__(self,word):
		self.word = word

	def word_count(self):
		exclude = set(string.punctuation)
		self.word = ''.join(ch for ch in self.word if ch not in exclude)
		print self.word
		return collections.Counter(self.word.lower().replace("  "," ").split(" "))
