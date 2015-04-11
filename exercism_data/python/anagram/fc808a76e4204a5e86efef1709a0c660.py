class Anagram:
	def __init__(self, word):
		self.word = word
		
	def match(self, words):
		return [word for word in words if self.is_anagram(word)]
		
	def is_anagram(self, new_word):
		if ((len(new_word) == len(self.word)) and (self.word != new_word)):
			for character in self.word.lower():
				if self.word.lower().count(character) != new_word.lower().count(character):
					return False
			return True
		else:
			return False
