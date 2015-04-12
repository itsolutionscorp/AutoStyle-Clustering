class Phrase(object):
	def __init__(self, sentence):
		self.sentence = sentence

	def word_count(self):
		answer = {}
		words = self.sentence.lower().translate(None, '!!&@$%^&:,').split()
		for word in words:
			i = words.count(word)
			answer[word] = i

		return answer