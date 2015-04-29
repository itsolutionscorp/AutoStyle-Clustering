# -*- coding: utf-8 -*-
import collections
def detect_anagrams(word, candidates):
    """
    detect_anagrams(str, list of str) -> list of str
    Return the list of strings from candidates that are anagrams to word.
    """
    word = word.lower()
    word_key = collections.Counter(word)
    return [can for can in candidates
                  if word != can.lower()
                    and word_key == collections.Counter(can.lower())]
