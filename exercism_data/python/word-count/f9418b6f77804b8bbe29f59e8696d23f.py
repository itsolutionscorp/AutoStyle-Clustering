__author__ = 'octowl'

from collections import Counter

def word_count(phrase):
    return Counter(phrase.split())
