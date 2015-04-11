import collections

class Anagram(object):
	def __init__(self, word):
		self._word = word or ""
		# _w is the normalized version of word.
		self._w = None
		# _char_count is a dict containing the count of each character in _w.
		self._char_count = None

	def match(self, strings):
		# The following code works in Python 2 but not Python 3.
		#
		#    return filter(self._is_anagram, strings)
		#
		# I don't perticularly like the syntax of list comprehension, but I guess
		# it's one of those things you simple get used to after a while. Any other
		# suggestions on a short version that works in both? :)
		#
		#    return [string for string in strings if self._is_anagram(string)]

		matches = []
		for string in strings:
			if self._is_anagram(string):
				matches.append(string)
		return matches

	def _is_anagram(self, string):
		# Normalize word only once.
		if not self._w:
			self._w = self._word.lower()
		# Calculate _char_count only once.
		if not self._char_count:
			self._char_count = collections.Counter(self._w)
		if len(string) != len(self._w):
			# Length differ, can't be an anagram.
			return False
		s = string.lower()
		if s == self._w:
			# Ignore "anagrams" that are identical to the original word.
			return False
		# char_count is a dict containing the count of each character in s.
		char_count = collections.Counter(s)
		if self._char_count == char_count:
			return True
		return False
