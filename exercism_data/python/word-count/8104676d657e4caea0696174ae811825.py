# -*- coding: utf-8 -*-
import string


def word_count(phrase):
    counts = {}

    # Remove punctuation and normalize the input
    phrase = ''.join((
        c.lower() for c in phrase if c not in string.punctuation))

    whole_words = phrase.split()
    single_words = set(whole_words)

    for word in single_words:
        counts[word] = len([w for w in whole_words if w == word])

    return counts
