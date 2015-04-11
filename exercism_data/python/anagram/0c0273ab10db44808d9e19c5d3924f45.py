class Anagram(object):
    def __init__(self, word):
        self._original = word.lower()
        self._alphagram = _alphagram(word)

    def _distinct_anagram(self, word):
        distinct = word.lower() != self._original
        anagram = _alphagram(word) == self._alphagram
        return distinct and anagram

    def match(self, words):
        return [word for word in words if self._distinct_anagram(word)]

def _alphagram(string):
    return sorted(string.lower())
