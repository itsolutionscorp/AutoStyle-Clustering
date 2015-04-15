from string import punctuation

class Phrase:
	def __init__ (self, phrase):
		self.phrase = phrase

	def word_count(self):
		phrase = reduce(lambda p, c: p.replace(c, ''), punctuation, self.phrase)
		word_dict = dict()
		for word in phrase.lower().split(' '):
			if not word: continue
			if word in word_dict:
				word_dict[word] += 1
			else:
				word_dict[word] = 1
		return word_dict
