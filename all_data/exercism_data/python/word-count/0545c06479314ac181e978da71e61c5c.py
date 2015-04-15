# -*- coding: utf-8 -*-
import string


def word_count(phrase):
    counts = {}

    # Remove punctuation and normalize the input
    phrase = ''.join((
        c.lower() for c in phrase if c not in string.punctuation))

    for word in phrase.split():
        counts[word] = counts.get(word, 0) + 1

    return counts
