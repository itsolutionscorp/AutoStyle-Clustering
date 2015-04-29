class Anagram(object):

    def __init__(self, word):
        self.word = word
        self._lower = word.lower()
        self._signature = sorted(self._lower)

    def is_anagram_to(self, other):
        """Check if self and other are anagrams."""
        return ((other._signature == self._signature) and
                (other._lower != self._lower))

    def match(self, candidate_words):
        """Return only those candidate words that are anagrams of self.word."""
        candidate_anagrams = (Anagram(c) for c in candidate_words)
        return [a.word for a in candidate_anagrams if self.is_anagram_to(a)]
