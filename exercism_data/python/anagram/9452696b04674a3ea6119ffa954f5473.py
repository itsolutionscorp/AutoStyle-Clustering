class Anagram:
	def __init__(self, word):
		self.word = word.lower()
		self.key = sorted(word.lower())

	def match(self, words):
		anagrams = list()
		for word in words:
			if sorted(word.lower()) == self.key and word != self.word:
				anagrams.append(word)
		return anagrams
