class Anagram:
	
	def __init__(self, word):
		self.word = word

	def are_anagrams(self, word1, word2):
		return sorted(word1.lower()) == sorted(word2.lower())

	def match(self, wordlist):
		return [item for item in wordlist if self.are_anagrams(item, self.word)]
