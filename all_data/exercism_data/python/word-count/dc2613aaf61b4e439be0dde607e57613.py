#!/usr/bin/python

def getWords(string):
    acc = ""
    for c in string:
        if c.isalnum():
            acc += c.lower()
        elif acc:
            yield acc
            acc = ""
    if acc:
        yield acc

def wordCount(string):
    words = {}
    for word in getWords(string):
        words[word] = words.get(word, 0) + 1
    return words
