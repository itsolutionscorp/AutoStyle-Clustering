import string

class Phrase(object):
	def __init__(self, phrase):
		self.phrase = phrase.lower().translate(None, string.punctuation)
	
	def word_count(self):
		words = self.phrase.split()
		count = {}
		for i in range(0, len(words)):
			if words[i] in count:
				count[words[i]] += 1
			else:
				count[words[i]] = 1
		return count	
