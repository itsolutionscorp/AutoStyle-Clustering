import re

words_re = re.compile(r"\w+")

def words(s):
	return words_re.findall(s)

def count(objs):
	res = {}
	for obj in objs:
		if obj in res:
			res[obj] += 1
		else:
			res[obj] = 1
	return res

class Phrase(object):
	def __init__(self, phrase):
		self.phrase = phrase
		self._word_count = None

	def word_count(self):
		if self._word_count is None:
			self._word_count = count(words(self.phrase.lower()))
		
		return self._word_count
