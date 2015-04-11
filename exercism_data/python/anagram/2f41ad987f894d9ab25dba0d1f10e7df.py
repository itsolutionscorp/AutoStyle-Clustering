class Anagram(object):
    def __init__(self, original):
        """Set original anagram word"""
        self._original = original

    def match(self, words):
        """Return array of words, containing same letters as the original
        nagram, case insensitive and not equal to original anagram"""
        result = []
        for word in words:
            if self._is_anagram(word):
                result.append(word)
        return result

    def _is_anagram(self, word):
        """Return true if word is anagram of the original"""
        return self._not_original(word) and self._same_letters(word)

    def _not_original(self, word):
        """Return true if letter order in word is different from original"""
        return self._original.lower() != word.lower()

    def _same_letters(self, word):
        """Return true if word containing same letters as the original"""
        return self._signature(self._original) == self._signature(word)

    def _signature(self, word):
        """Return array of letters in word, sorted alphabetically"""
        return sorted(word.lower())
