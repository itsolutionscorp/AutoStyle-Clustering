#!/usr/bin/env python

def distance(first, second):
    """ Calculates the hamming distance between 2 strings.

    Pre-condition:
        first and second must be strings of the same length.
    """
    dist = 0

    for ch1, ch2 in zip(first, second):
        if ch1 != ch2:
            dist += 1

    return dist
