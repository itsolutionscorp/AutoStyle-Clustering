from itertools import groupby
from string import punctuation
from re import split

class Phrase:
    def __init__(self, phrase):
        phrase = phrase.translate(None, punctuation).lower()
        words = split('\s+', phrase)
        self.grouped_words = self._group_words(words)

    def word_count(self):
        return self.grouped_words 

    def _group_words(self, phrase):
        word_counts = {}
        for word in phrase:
            word_counts[word] = word_counts.get(word, 0) + 1

        return word_counts
