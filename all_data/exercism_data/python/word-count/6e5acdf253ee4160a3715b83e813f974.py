# -*- coding: utf-8 -*-

from collections import defaultdict

class Phrase:

    def __init__(self, phrase: str):
        self.counts = defaultdict(int)
        for word in phrase.split(' '):
            k = ''.join(c.lower() for c in word if c.isalnum())
            if k:
                self.counts[k] += 1

    def word_count(self) -> int:
        return self.counts
