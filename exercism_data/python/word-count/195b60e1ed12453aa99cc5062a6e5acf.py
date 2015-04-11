__author__ = 'Cedric Zhuang'

from collections import Counter


def word_count(s):
    return Counter(s.split())
