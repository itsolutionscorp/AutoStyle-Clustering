class Anagram(object):
    def __init__(self, word):
        self.word = word
        self.canonical = self._canonicalize(word)

    def _canonicalize(self, word):
        return sorted(word.lower())

    def _is_anagram(self, word):
        return word != self.word and self._canonicalize(word) == self.canonical

    def match(self, words):
        return filter(self._is_anagram, words)
