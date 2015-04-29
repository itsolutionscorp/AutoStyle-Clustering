#!/usr/bin/env python

from collections import defaultdict

def word_count(phrase):
    x = defaultdict(int)
    for i in phrase.split():
        x[i] += 1

    return x
