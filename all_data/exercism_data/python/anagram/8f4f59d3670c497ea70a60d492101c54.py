#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# Submission file for the python anagram exercise.
#
# v1: use sorted strings, and append anagrams to a pre-defined list


def detect_anagrams(word, candidates):
    """
    Return all correct anagrams of a given word from a list of words.

    Anagrams are case insensitive, and differ from the original word.
    """

    anagrams = []

    for candidate in candidates:
        c = candidate.lower()
        w = word.lower()

        if sorted(c) == sorted(w) and c != w:
            # we used case insensitive comparison, but the original
            # candidate is appended to the anagram list
            anagrams.append(candidate)

    return anagrams
