#!/usr/bin/env python3

from collections import Counter

def word_count(paragraph):
    words = paragraph.split()
    return Counter(words)
