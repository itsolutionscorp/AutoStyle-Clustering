#!/usr/bin/env python3

from collections import Counter
from string import punctuation

strip_punctuation = lambda s: s.strip(punctuation)


class Phrase:
    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        words = self.phrase.split()
        words = map(strip_punctuation, words)
        words = map(str.lower, words)
        words = filter(None, words)
        return Counter(words)
