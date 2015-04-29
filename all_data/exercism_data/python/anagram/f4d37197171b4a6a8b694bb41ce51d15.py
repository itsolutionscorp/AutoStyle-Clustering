class Anagram(object):
    def __init__(self, original):
        """Set original anagram word"""
        self._original = original.lower()

    def match(self, words):
        """Return array of words, containing same letters as the original
        nagram, case insensitive and not equal to original anagram"""
        result = []
        for word in words:
            if self._is_anagram(word.lower()):
                result.append(word)
        return result

    def _is_anagram(self, word):
        """Return true if word is anagram of the original"""
        return self._original != word and self._same_letters(word)

    def _same_letters(self, word):
        """Return true if word containing same letters as the original"""
        return sorted(self._original) == sorted(word)
