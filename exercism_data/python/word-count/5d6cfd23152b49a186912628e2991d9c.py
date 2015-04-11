#!/usr/bin/env python3

from collections import defaultdict

def word_count(phrase):
    count = defaultdict(int)
    for word in phrase.split():
        count[word] += 1
    return count
