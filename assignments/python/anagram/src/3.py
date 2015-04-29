#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""
Anagram
"""
__version__ = "0.0.1"
__all__ = ["__version__", "detect_anagrams"]
def detect_anagrams(word, word_list):
    """Given a word and a list of possible anagrams,
    selects the correct sublist.
    .. versionadded:: 0.0.1
    :param word: the word
    :param word_list: list of possible anagrams
    """
    _length = lambda a: len(a) == len(word)
    _same = lambda a: a.lower() != word.lower()
    _sorted = lambda a: sorted(a.lower()) == sorted(word.lower())
    return filter(lambda a: _length(a) and _same(a) and _sorted(a), word_list)
