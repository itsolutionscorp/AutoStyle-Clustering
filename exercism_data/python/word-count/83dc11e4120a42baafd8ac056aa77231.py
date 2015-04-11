import collections
import re

class Phrase(object):
	def __init__(self, s):
		self.s = s
		self.wc  = None

	def word_count(self):
		# Calculate wc only once.
		if not self.wc:
			words = re.findall("[a-z\d]+", self.s.lower())
			self.wc = collections.Counter(words)
		return self.wc
