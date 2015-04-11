#!/usr/bin/env python

from string import punctuation, maketrans, translate
from collections import Counter


def word_count(phrase):
    return Counter(translate(phrase.lower(),
                             maketrans(punctuation,
                                       ' '*len(punctuation))).split())
