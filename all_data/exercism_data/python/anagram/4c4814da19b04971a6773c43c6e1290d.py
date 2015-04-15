#!/usr/bin/env python2.7
# -*- coding: utf-8 -*-

class Anagram(object):

  def __init__(self, word):
    self._word = word
    self._canonical = sorted(word.lower())

  def match(self, words):
    return filter(self._is_anagram, words)

  def _is_anagram(self, word):
    canonical_word = sorted(word.lower())
    return canonical_word == self._canonical and word != self._word.lower()
