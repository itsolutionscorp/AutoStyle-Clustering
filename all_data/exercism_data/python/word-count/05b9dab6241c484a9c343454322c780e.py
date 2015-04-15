"""
Exercism.io
Python Word Count

Author: jdcampo
"""

def word_count(phrase):
    words = {};
    for w in phrase.split():
        if w not in words:
            words[w] = 1
        else:
            words[w] += 1
    return words
