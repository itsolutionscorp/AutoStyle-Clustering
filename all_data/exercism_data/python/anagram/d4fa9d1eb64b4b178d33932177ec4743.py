from collections import Counter

class Anagram(object):
    def __init__(self, word):
        self.word = word.lower()
        self._char_freqs = self._char_counts(self.word)

    def _char_counts(self, word):
        return Counter(word)

    def is_anagram(self, word):
        word = word.lower()
        return self.word != word and \
            self._char_counts(word) == self._char_freqs

    def match(self, words):
        return filter(self.is_anagram, words)
