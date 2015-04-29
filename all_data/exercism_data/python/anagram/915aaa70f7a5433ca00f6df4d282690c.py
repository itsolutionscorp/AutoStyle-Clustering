class Anagram:
	def __init__(self, anagram):
		self.anagram = anagram

	def match(self, words):
		anagrams = []
		for word in words:
			if len(self.anagram) != len(word) or word == self.anagram:
				continue
			else:
				anagram_chars = list(self.anagram.lower())
				word_chars = list(word.lower())
				anagram_chars.sort()
				word_chars.sort()
				if anagram_chars == word_chars:
					anagrams.append(word)

		return anagrams
