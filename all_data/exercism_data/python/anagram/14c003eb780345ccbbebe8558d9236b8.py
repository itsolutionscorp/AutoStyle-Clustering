class Anagram(object):
    def __init__(self, original):
        """Set original anagram word"""
        self._original = original.lower()

    def match(self, words):
        """Return array of words, containing same letters as the original
        nagram, case insensitive and not equal to original anagram"""
        return filter(self._is_anagram, words)


    def _is_anagram(self, word):
        """Return true if word is anagram of the original"""
        return self._original != word and self._same_letters(word.lower())

    def _same_letters(self, word):
        """Return true if word containing same letters as the original"""
        return sorted(self._original) == sorted(word)
