class Anagram:
	def __init__(self, word):
		self.word = word

	def match(self, los):
		key = ''.join(sorted(self.word.lower()))
		result = []
		for word in los:
			sorted_word = ''.join(sorted(word.lower()))
			if sorted_word == key and word != self.word:
				result.append(word)
		return result
