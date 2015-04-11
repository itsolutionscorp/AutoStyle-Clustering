# -*- coding: utf-8 -*-

class Anagram(object):

  def __init__(self, word):
    self._word = word

  def match(self, anagrams):
    results = []
    sorted_word = sorted(self._word.lower())

    for a in anagrams:
      sa = sorted(a.lower())
      if sa == sorted_word and a != self._word.lower():
        results.append(a)

    return results
