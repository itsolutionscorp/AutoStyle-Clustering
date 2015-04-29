#!/usr/bin/env python

def word_count(sentence):
    words = sentence.split()
    collection = dict()

    for word in words:
        if word in collection:
            collection[word] += 1
        else:
            collection[word] = 1

    return collection
