#! /usr/bin/env python


class Anagram(object):
    def __init__(self, word):
        self.word = word.lower()
        self.letters = sorted(self.word)

    def match(self, words):
        """(list) -> list"""
        return [w for w in words if self.is_anagram(w.lower())]

    def is_anagram(self, word):
        return self.word != word and self.letters == sorted(word)
