#!/usr/bin/python

import collections

def getWords(string):
    acc = []
    for c in string:
        if c.isalnum():
            acc.append(c.lower())
        elif acc:
            yield "".join(acc)
            acc = []
    if acc:
        yield "".join(acc)

def wordCount(string):
    words = collections.defaultdict(int)
    for word in getWords(string):
        words[word] += 1
    return words
