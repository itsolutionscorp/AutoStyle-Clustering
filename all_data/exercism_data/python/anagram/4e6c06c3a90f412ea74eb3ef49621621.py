import collections

class Anagram(object):
	def __init__(self, word):
		# _word is the normalized version of word.
		word = word or ""
		self._word = word.lower()
		# _char_count is a dict containing the count of each character in _word.
		self._char_count = collections.Counter(self._word)

	def match(self, strings):
		# The following code works in Python 2 but not Python 3.
		#
		#	return filter(self._is_anagram, strings)
		#
		# I don't perticularly like the syntax of list comprehension, but I guess
		# it's one of those things you simple get used to after a while. Any other
		# suggestions on a short version that works in both? :)
		#
		#	return [string for string in strings if self._is_anagram(string)]

		matches = []
		for string in strings:
			if self._is_anagram(string):
				matches.append(string)
		return matches

	def _is_anagram(self, string):
		if len(string) != len(self._word):
			# Length differ, thus string cannot be an anagram.
			return False
		s = string.lower()
		if s == self._word:
			# Anagrams should differ from the original word.
			return False
		# char_count is a dict containing the count of each character in s.
		char_count = collections.defaultdict(int)
		for c in s:
			if not c in self._char_count:
				# If the character c isn't present in _char_count s cannot be
				# an anagram of _word.
				return False
			char_count[c] += 1
		if self._char_count == char_count:
			return True
		return False
