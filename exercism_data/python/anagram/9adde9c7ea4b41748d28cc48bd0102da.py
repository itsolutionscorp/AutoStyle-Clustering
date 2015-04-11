# -*- coding:utf-8 -*-

class Anagram(object):
    def __init__(self, word):
        self._word = word.lower()
        self._letters = sorted(self._word)

    def is_anagram_of(self, other):
        try:
            return self._word != other._word and self._letters == other._letters
        except AttributeError, e:
            return False

    def match(self, words):
        return [word for word in words if Anagram(word).is_anagram_of(self)]
