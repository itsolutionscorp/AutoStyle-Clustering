from collections import Counter

class Anagram(object):
    def __init__(self, word):
        self.word = word
        self._canon_word = self._canonicalize(word)

    def _canonicalize(self, word):
        return Counter(word.lower())

    def is_anagram(self, word):
        return self.word != word and \
            self._canonicalize(word) == self._canon_word

    def match(self, words):
        return filter(self.is_anagram, words)
