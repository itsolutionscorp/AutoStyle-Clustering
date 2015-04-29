class Anagram(str):
	"""Represents an anagram"""
	def __init__(self, word):
		super(Anagram, self).__init__(word)
		lower = self.lower() #Anagram checking is case insensitive
		self._sort_cache = sorted(lower) #Anagrams are immutable, so the result of sorting will always be the same
	def match(self, possible):
		"""Filters a list of possible anagrams of the word, down to those that are correct anagrams"""
		return filter(self.check_match,possible)
	
	def check_match(self, word):
		"""Check if a word is a correct anagram match"""
		return word.lower() != self.lower() and sorted(word.lower()) == self._sort_cache
