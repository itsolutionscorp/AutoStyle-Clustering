from collections import Counter


def word_count(phrase):
    """
    Count the occurrences of each word in a phrase.

    Returns a dict of words, where each value is the associated count.
    """
    return Counter(phrase.split())
