from collections import Counter


def word_count(phrase):
    """The function takes a sentence and count the words using
        collections, This module implements specialized container datatypes."""

    count = Counter()

    for word in phrase.split():
        count[word] += 1

    return count
