class Phrase(object):
	def __init__(self, sentence):
		self.sentence = sentence

	def word_count(self):
		answer = {}
		words = self.sentence.split()

		for word in words:
			i = words.count(word)
			answer[word] = i

		return answer
