class Anagram(object):
	NUM_LETTERS = 26
	def __init__(self, word):
		self.word = word
		self.canonicalizedWord = Anagram._canonicalize_word(word)

	@staticmethod
	def _canonicalize_word(word):
		return sorted(word.lower())

	def _is_anagram_of(self, candidate):
		if self.word == candidate:
			return False
		return Anagram._canonicalize_word(candidate) == self.canonicalizedWord

	def match(self, cand_list):
		return [c for c in cand_list if self._is_anagram_of(c)]
