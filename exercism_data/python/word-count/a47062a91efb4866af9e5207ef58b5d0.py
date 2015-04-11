from collections import Counter
import re

class TextPhrase(object):

	__word_re = re.compile(r'\w+', re.UNICODE)

	def __init__(self, textphrase):
		self.textphrase = textphrase

	def words(self):
		return (m.group(0) for m in self.__word_re.finditer(self.textphrase))

	def word_count(self):
		return Counter(word.lower() for word in self.words())
