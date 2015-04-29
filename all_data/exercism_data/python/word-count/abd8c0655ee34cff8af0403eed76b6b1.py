#!/usr/bin/env python3
from collections import defaultdict

def word_count(words):
    result = defaultdict(int)
    for word in words.split():
        result[word] += 1
    return result
