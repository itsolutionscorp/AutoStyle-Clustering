class Phrase(object):
	def __init__(self, sentence):
		self.sentence = sentence

	def word_count(self):
		answer = {}
		
		for word in self.sentence.lower().translate(None, '!!&@$%^&:,'):
			i = words.count(word)
			answer[word] = i

		return answer
