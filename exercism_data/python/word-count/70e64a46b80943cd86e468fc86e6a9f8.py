import re
import collections
class Phrase:
	def __init__(self, phrase):
		self.phrase = phrase

	def word_count(self):
		words = re.findall('\w+', self.phrase.lower())
		word_count = collections.Counter(words).most_common()

		return dict(word_count)
