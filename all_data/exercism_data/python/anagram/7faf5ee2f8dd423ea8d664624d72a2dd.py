class Anagram(object):
    def __init__(self, word):
        self._original = word.lower()
        self._alphagram = _alphagram(word)

    def match(self, words):
        return [word for word in words
                if _alphagram(word) == self._alphagram
                and word != self._original]


def _alphagram(string):
    return ''.join(sorted(string.lower()))
