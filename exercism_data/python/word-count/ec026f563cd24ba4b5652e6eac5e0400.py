#!/usr/bin/env python

def word_count(phrase):
    """ given a phrase, counts the number ofoccurrences of each word"""
    words = dict()
    for word in phrase.split():
        words[word] = words.get(word, 0) + 1
    return words
