import re
from collections import Counter

class Phrase:
	def __init__(self, phrase):
		self.phrase = phrase

	def strip_non_alphas(self, str):
		return re.sub(r'([^\s\w]+)', '', str)

	def word_count(self):
		phrase = self.phrase.lower()
		phrase = self.strip_non_alphas(phrase)
		return Counter(phrase.split())
