from __future__ import unicode_literals

class Anagram(object):
    """
    Matches anagrams by comparing sorted characters
    """
    def __init__(self, word):
        self.word = word.lower()
        self.sorted = sorted(self.word)

    def _matches(self, candidate):
        """
        Returns True if candidate is an anagram of our word (but not equal)
        """
        c = candidate.lower()
        return sorted(c) == self.sorted and self.word != c

    def match(self, candidates):
        return filter(self._matches, candidates)
