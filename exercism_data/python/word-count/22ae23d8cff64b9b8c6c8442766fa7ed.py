import re

class Phrase(object):
	def __init__(self, phrase):
		self.phrase = phrase
		
		
	def word_count(self):
		counts = {}
		words = self.word_list()
		for word in words:
			counts[word] = words.count(word)
		return counts
		
	def word_list(self):
		regex = re.compile("\w+")
		return regex.findall(self.phrase.lower())
