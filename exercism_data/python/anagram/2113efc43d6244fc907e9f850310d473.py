class Anagram:
	def __init__(self, word):
		self.word = word.lower()
		self.sorted_word = sorted(self.word)

	def match(self, anagrams):
		is_anagram = lambda word: word.lower() != self.word and sorted(word.lower()) == self.sorted_word
		return filter(is_anagram, anagrams)
