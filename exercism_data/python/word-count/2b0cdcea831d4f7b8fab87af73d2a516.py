import re

class Phrase(object):
	def __init__(self, s):
		self.s = s

	def word_count(self):
		words = filter(None, re.split("[^a-z0-9]", self.s.lower()))
		d = {}
		for word in words:
			if word in d:
				d[word] += 1
			else:
				d[word] = 1
		return d
