import collections

class Anagram(object):
	def __init__(self, word):
		self.word = word or ""
		self.wc = None

	def match(self, l):
		word = self.word.lower()
		# Calculate wc only once.
		if not self.wc:
			self.wc = collections.Counter(word)
		matches = []
		for anagram in l:
			a = anagram.lower()
			if a == word:
				# Ignore "anagrams" that are identical to the original word.
				continue
			ac = collections.Counter(a)
			if self.wc == ac:
				matches.append(anagram)
		return matches
