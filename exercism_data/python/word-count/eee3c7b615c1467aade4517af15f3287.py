from collections import Counter
from re import split


def word_count(string):
    result = dict(Counter(split(r'[^0-9a-z]+', string.lower())))
    if '' in result:
        result.pop('')
    return result
