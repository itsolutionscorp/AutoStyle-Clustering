class Anagram:
    """Anagram words have the same letters in different order."""

    def __init__(self, word):
        self.normalized = self._normalize(word)

    def match(self, words):
        """Return the anagrams from the list of words."""
        return [w for w in words if self._normalize(w) == self.normalized]

    def _normalize(self, word):
        """Normalize the word: sort its lowercased letters."""
        return sorted(word.lower())
