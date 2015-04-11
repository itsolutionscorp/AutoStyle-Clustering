"""

Write a program that given a phrase can count the occurrences of each word in that phrase.

For example for the input `"olly olly in come free"`

plain
olly: 2
in: 1
come: 1
free: 1

"""

from collections import defaultdict


def word_count(phrase):
    dictionary = defaultdict(int)

    words = phrase.split()

    for word in words:
        dictionary[word] += 1

    return dict(dictionary)
