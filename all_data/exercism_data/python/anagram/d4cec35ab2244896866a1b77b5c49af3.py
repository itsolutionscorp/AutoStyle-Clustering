class Anagram:
	
	def __init__(self, word):
		self.word = word
		self.sorted_word = sorted(word.lower())
		
	def is_anagram(self, item):
		return sorted(item.lower()) == self.sorted_word
		
	def match(self, wordlist):
		return [item for item in wordlist if self.is_anagram(item)]
