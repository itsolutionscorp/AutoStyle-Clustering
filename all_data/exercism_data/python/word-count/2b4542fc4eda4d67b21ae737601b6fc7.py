#!/usr/bin/env python3

def word_count(text):
    """Count the occurrences of each word in a phrase.
    returns a dict { word: occurrence }."""
    words = text.strip().split()
    counted = {}
    for word in words:
        if word in counted:
            counted[word] += 1
        else:
            counted[word] = 1
    return counted
