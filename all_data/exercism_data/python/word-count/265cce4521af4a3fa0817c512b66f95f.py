#!/usr/bin/python
from collections import defaultdict

class Phrase(object):
    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        words = self.phrase.split(' ')
        words = [w.lower() for w in words]
        wc = defaultdict(lambda: 0)
        for w in words:
            w = ''.join((c for c in w if c.isalnum()))
            if w:
                wc[w] += 1
        return dict(wc)
