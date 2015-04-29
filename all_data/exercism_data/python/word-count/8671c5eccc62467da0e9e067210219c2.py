"""This module implements a naive word-counter function."""

def word_count(what):
    """Returns a dict of words in the input, where each word's associated
    value is its frequency in the input. A 'word' is considered to be a
    contiguous block of non-whitespace characters."""
    list = what.split()
    d = dict()
    
    for w in list:
        if w in d:
            d[w] += 1
        else:
            d[w] = 1
    
    return d
