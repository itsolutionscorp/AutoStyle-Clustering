from collections import Counter


class Anagram(object):
    def __init__(self, word):
        self.word = word
        self.word_occurrences = self._occurrences(word)

    def match(self, candidates):
        return [c for c in candidates if self._is_anagram(c)]

    def _occurrences(self, word):
        return Counter(word.lower())

    def _is_anagram(self, candidate):
        return (self.word != candidate 
            and self._occurrences(candidate) == self.word_occurrences)
