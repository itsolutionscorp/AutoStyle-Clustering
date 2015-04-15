import collections
import re

class Phrase(object):
	def __init__(self, s):
		self._s = s
		self._wc  = None

	def word_count(self):
		# Calculate wc only once.
		if not self._wc:
			words = re.findall("[a-z\d]+", self._s.lower())
			self._wc = collections.Counter(words)
		return self._wc
