import re, collections

class Phrase(object):
	"""Class representing phrase to make word count on."""
	def __init__(self, phrase):
		self.phrase = phrase

	def word_count(self):
		"""Return dictionary with words statistic of current phrase."""
		return collections.Counter(re.findall('\w+', self.phrase.lower()))

