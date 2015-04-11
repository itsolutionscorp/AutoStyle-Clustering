from collections import Counter

class Anagram:
	def __init__(self, word):
		self.word = word
		self.word_counter = Counter(word.lower())
		
	def match(self, words):
		return [word for word in words if self.is_anagram(word)]

	def is_anagram(self, new_word):
		if (self.word != new_word):
			new_word_counter = Counter(new_word.lower())
			return (list(self.word_counter.elements()) == list(new_word_counter.elements()))
		else:
			return False
