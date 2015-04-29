class Anagram:
	def __init__(self, word):
		self.lowerWord = word.lower()
		self.sortedLetters = sorted(self.lowerWord)

	def match(self, list):
		return filter(self.anagram, list)

	def anagram(self, word):
		lowerWord = word.lower()
		return sorted(lowerWord) == self.sortedLetters and \
			lowerWord != self.lowerWord
