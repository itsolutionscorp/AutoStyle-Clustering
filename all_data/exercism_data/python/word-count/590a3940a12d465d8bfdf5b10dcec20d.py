class Phrase:
	def __init__(self, phrase):
		self.phrase = phrase
	def word_count(self):
		import re
		count = {}
		for word in self.phrase.split(' '):
			word=re.sub("\W","",word.lower())
			if(not word):
				continue
			if(word in count):
				count[word] += 1
			else:
				count[word] = 1
		return count
