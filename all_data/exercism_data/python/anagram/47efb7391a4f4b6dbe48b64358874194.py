import collections

class Anagram(object):
	def __init__(self, word):
		self._word = word or ""
		self._wc = None

	def match(self, l):
		word = self._word.lower()
		# Calculate wc only once.
		if not self._wc:
			self._wc = collections.Counter(word)
		matches = []
		for anagram in l:
			if len(anagram) != len(word):
				# Ignore if length doesn't match.
				continue
			a = anagram.lower()
			if  a == word:
				# Ignore "anagrams" that are identical to the original word.
				continue
			ac = collections.Counter(a)
			if self._wc == ac:
				matches.append(anagram)
		return matches
