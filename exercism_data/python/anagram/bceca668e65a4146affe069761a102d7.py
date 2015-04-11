class Anagram(str):
	def __init__(self, word):
		super(Anagram, self).__init__(word)
		lower = self.lower()
		self._sort_cache = sorted(lower)
	def match(self, possible):
		return filter(self.check_match,possible)
	
	def check_match(self, word):
		return word.lower() != self.lower() and sorted(word.lower()) == self._sort_cache
