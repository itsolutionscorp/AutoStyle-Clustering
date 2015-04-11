from collections import Counter

class Anagram:
	def __init__(self, word):
		self.word = word
		
	def match(self, words):
		return [word for word in words if self.is_anagram(word)]
		
	def is_anagram(self, new_word):
		if ((len(new_word) == len(self.word)) and (self.word != new_word)):
			word_counter = Counter()
			for character in self.word.lower():
				word_counter[character] += 1
				
			new_word_counter = Counter()
			for character in new_word.lower():
				new_word_counter[character] += 1
	
			return (list(word_counter.elements()) == list(new_word_counter.elements()))
		else:
			return False
