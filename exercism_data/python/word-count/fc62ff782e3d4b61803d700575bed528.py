class Phrase(object):
	def __init__(self, sentence):
		self.sentence = sentence

	def word_count(self):
		answer = {}
		s0 = self.sentence.lower()
		s1 = s0.replace(':',' ')
		s2 = s1.replace(',', ' ')
		s3 = s2.replace('!!&@$%^&', ' ')
		words = s3.split()

		for word in words:
			i = words.count(word)
			answer[word] = i

		return answer
