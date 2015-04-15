import re

class Phrase(object):
	def __init__(self, string):
		self.normalized = string.lower()


	def word_count(self):
		counts = {}
		no_punctuation = re.findall(r'\w+', self.normalized)	
		for word in no_punctuation:
			if counts.get(word) is not None:
				counts[word] +=1
			else:
				counts[word] = 1		
		return counts

		
