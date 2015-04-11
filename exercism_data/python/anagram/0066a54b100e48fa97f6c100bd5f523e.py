# -*- coding: utf-8 -*-

class Anagram:

    def __init__(self, word):
        self._word = word
        self._normalized = self._normalize(word)

    def match(self, words):
        return [w for w in words if self._is_match(w)]

    def _is_original(self, word):
        return self._word == word

    def _is_match(self, word):
        return not self._is_original(word) \
            and self._normalized == self._normalize(word)

    def _normalize(self, word):
        return sorted(word.lower().strip())
