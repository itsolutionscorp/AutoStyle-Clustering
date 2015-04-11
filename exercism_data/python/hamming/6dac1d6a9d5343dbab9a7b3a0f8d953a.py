#!/usr/bin/env python

from itertools import zip_longest

def hamming(one, two):
    distance = 0

    for (a, b) in zip_longest(one, two, fillvalue=''):
        if a != b:
            distance += 1

    return distance
