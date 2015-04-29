class Anagram(object):
    def __init__(self, original):
        self._original = original

    def match(self, words):
        result = []
        for word in words:
            if self._is_anagram(word):
                result.append(word)
        return result

    def _is_anagram(self, word):
        return self._not_original(word) and self._same_letters(word)

    def _not_original(self, word):
        return self._original.lower() != word.lower()

    def _same_letters(self, word):
        return self._signature(self._original) == self._signature(word)

    def _signature(self, word):
        return sorted(word.lower())
