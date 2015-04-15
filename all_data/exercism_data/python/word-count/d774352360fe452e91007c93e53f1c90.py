__author__ = 'linda'

from collections import defaultdict

def word_count(phrase):
    assert isinstance(phrase, str)
    counts = defaultdict(int)
    for word in phrase.split():
        counts[word] += 1
    return counts
