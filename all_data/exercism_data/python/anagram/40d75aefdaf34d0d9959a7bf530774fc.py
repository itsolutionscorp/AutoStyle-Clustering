class Anagram(object):
	NUM_LETTERS = 26
	def __init__(self, string):
		self.string = string
		self.freq = Anagram._freq_array(self.string)

	def _is_anagram_of(self, candidate):
		if self.string == candidate:
			return False
		cand_freq = Anagram._freq_array(candidate)
		for i in xrange(0, Anagram.NUM_LETTERS):
			if self.freq[i] != cand_freq[i]:
				return False
		return True

	@staticmethod
	def _freq_array(string):
		freq = [0] * Anagram.NUM_LETTERS
		for c in string:
			freq[ord(c.lower())-ord('a')] += 1
		return freq

	def match(self, cand_list):
		return [c for c in cand_list if self._is_anagram_of(c)]
