#!/usr/bin/env python
# encoding=utf-8

from string import maketrans, punctuation
from collections import Counter

def normalize(phrase):
    """Take a string, strip punctuation and turn it to lowercase"""

    trans_table = maketrans("", "")
    "create an empty translation table"

    result = phrase.translate(trans_table, punctuation)
    "does no translation, but strips all punctuation"

    result = result.lower()
    return result


class Phrase(object):
    """Phrase class"""

    def __init__(self, phrase):
        self.phrase = normalize(phrase)

    def word_count(self):
        """Return a dictionary with words as keys and word counts as values"""
        return Counter(self.phrase.split())
