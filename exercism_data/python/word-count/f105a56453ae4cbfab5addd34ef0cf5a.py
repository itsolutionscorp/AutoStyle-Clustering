import collections
import re

class Phrase(object):
	def __init__(self, s):
		self.s = s

	def word_count(self):
		words = re.findall("[a-z\d]+", self.s.lower())
		d = collections.defaultdict(int)
		for word in words:
			d[word] += 1
		return d
