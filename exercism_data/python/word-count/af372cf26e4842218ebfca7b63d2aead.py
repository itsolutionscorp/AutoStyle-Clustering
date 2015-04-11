"""
A solution to the word-count problem on Exercism.io
"""

from collections import defaultdict


def word_count(phrase):
    """
    Count the occurrences of each word in a phrase.

    Returns a dict of words, where each value is the associated count.
    """
    counter = defaultdict(lambda: 0)
    for word in phrase.split():
        counter[word] += 1
    return dict(counter)
