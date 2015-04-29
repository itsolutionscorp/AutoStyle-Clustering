from collections import Counter


def word_count(s):
    """
    Return a dict with 'word': number of occurrences for each word in `s`.
    """
    return Counter(s.split())
