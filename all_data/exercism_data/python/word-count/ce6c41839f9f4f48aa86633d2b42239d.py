__author__ = 'Chris'
# collections is amazing
from collections import Counter


def word_count(sentence):
    words = sentence.split()
    x = Counter(words)
    converted_dict = dict(x)
    return converted_dict
