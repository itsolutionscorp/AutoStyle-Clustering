import string

class Phrase(object):
	def __init__(self, phrase):
		super(Phrase, self).__init__()
		self.phrase = phrase
		
	def word_count(self):
		freqDist = {}
		translator = string.maketrans(string.punctuation, ' ' * len(string.punctuation))
		for w in self.phrase.lower().translate(translator).split():
			if w in freqDist.keys():
				freqDist[w] += 1
			else:
				freqDist[w] = 1
		return freqDist
