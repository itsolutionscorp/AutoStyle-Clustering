#!/usr/bin/env python

from string import punctuation

class Phrase:
    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        frequencies = {}
        for word in self.phrase.translate(None, punctuation).lower().split():
            if word in frequencies:
                frequencies[word] += 1
            else:
                frequencies[word] = 1
        return frequencies
