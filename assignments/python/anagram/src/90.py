# -*- coding: utf-8 -*-
import collections
def detect_anagrams(word, candidates):
    """
    detect_anagrams(str, list of str) -> list of str
    Return the list of strings from candidates that are anagrams to word.
    """
    word_lower = word.lower()
    word_key = collections.Counter(word_lower)
    anagrams = list()
    for candidate in candidates:
        candidate_lower = candidate.lower()
        if (word_lower != candidate_lower
            and word_key == collections.Counter(candidate_lower)):
            anagrams.append(candidate)
    return anagrams
