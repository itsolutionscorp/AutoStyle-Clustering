import re
from collections import defaultdict


def word_count(phrase):
    c = defaultdict(int)
    for word in re.split(r'\W+', phrase):
        if word:  # word can be empty string
            c[word.lower()] += 1
    return c
