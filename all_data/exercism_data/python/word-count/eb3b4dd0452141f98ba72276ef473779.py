import collections
import re

class Phrase(object):
	def __init__(self, s):
		self.s = s

	def word_count(self):
		words = re.findall("[a-z\d]+", self.s.lower())
		return collections.Counter(words)
