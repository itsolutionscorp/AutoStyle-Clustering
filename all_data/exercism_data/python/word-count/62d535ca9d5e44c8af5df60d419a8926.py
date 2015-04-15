__author__ = 'colinc'
from collections import Counter


def word_count(words):
    return {key: value for key, value in Counter((filter(str.isalnum, j) for j in words.lower().split())).iteritems()
            if key}
