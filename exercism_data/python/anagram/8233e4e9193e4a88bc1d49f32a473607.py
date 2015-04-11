class Anagram(object):

    def __init__(self, word):
        self._word = word.lower()
        self._sorted = sorted(self._word)

    def match(self, candidates):
        """Returns anagrams from 'candidates'.

        Args:
            candidates: List of strings.

        Returns:
            A sublist of 'candidates' consisting of those words which are
            anagrams of self._word.
        """

        return filter(self.is_anagram, candidates)

    def is_anagram(self, candidate):
        """Returns True if 'candidate' is an anagram of self._word."""

        exact_match = (self._word == candidate.lower())
        same_letters = (self._sorted == sorted(candidate.lower()))

        return same_letters and not exact_match
