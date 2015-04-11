# -*- coding:utf-8 -*-
class Word(object):
    def __init__(self, word):
        self._word = word.lower()
        self._letters = sorted(self._word)

    def is_anagram_of(self, other):
        try:
            return self._letters == other._letters and self._word != other._word
        except AttributeError, e:
            return False

class Anagram(object):
    def __init__(self, word):
        self._word = Word(word)

    def match(self, words):
        return [word for word in words if Word(word).is_anagram_of(self._word)]
