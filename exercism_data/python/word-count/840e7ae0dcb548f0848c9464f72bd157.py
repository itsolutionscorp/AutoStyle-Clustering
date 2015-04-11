#!/usr/bin/env python

"""
Write a program that given a phrase can count the occurrences of each word in that phrase.

For example for the input `"olly olly in come free"`
"""

from collections import Counter

def normalize_phrase(phrase):
    phrase = filter(lambda sym: sym.isalnum() or sym.isspace(), phrase)
    return phrase.lower()

class Phrase(object):

    def __init__(self, phrase):
        self._phrase = phrase

    def word_count(self):
        """
        Return the number of occurrences of each word.
        """
        phrase = normalize_phrase(self._phrase)
        words = phrase.split()
        return Counter(words)
