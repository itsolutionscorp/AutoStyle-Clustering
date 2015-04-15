import re

class Phrase(object):
	"""Class representing phrase to make word count on."""
	def __init__(self, phrase):
		self.phrase = phrase

	def word_count(self):
		"""Return dictionary with words statistic of current phrase."""
		wordcount = {}
		self.phrase = [word.lower() for word in re.split('\W+', self.phrase)]
		for word in self.phrase:
			if word:
				try:
					wordcount[word] += 1
				except:
					wordcount[word] = 1
		return wordcount

