class Anagram:
	def __init__(self, word):
		self.lowerWord = word.lower()
		self.sortedLetters = sorted(self.lowerWord)

	def match(self, words):
		return list(filter(self.anagram, words))

	def anagram(self, word):
		lowerWord = word.lower()
		return sorted(lowerWord) == self.sortedLetters and \
			lowerWord != self.lowerWord
