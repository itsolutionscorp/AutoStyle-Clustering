class Anagram:
	
	def __init__(self, word):
		self.word = word

	def is_anagram(self, item):
		return sorted(item.lower()) == sorted(self.word.lower())

	def match(self, wordlist):
		return [item for item in wordlist if self.is_anagram(item)]
