"""
Exercism.io
Python Word Count
Author: jdcampo
"""
def word_count(phrase):
    words = {};
    for w in phrase.split():
        if w not in words:
            words[w] = 0
        words[w] += 1
    return words
