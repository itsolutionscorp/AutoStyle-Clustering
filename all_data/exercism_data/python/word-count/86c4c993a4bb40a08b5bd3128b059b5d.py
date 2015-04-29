#!/usr/bin/env python3

from collections import Counter
from string import punctuation

strip_punctuation = lambda s: s.strip(punctuation)


class Phrase(str):
    def word_count(self):
        words = self.split()
        words = map(strip_punctuation, words)
        words = map(str.lower, words)
        words = filter(None, words)
        return Counter(words)
