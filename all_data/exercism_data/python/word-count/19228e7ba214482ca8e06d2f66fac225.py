# -*- coding: utf-8 -*-
"""
Created on Fri Sep 26 15:45:51 2014

@author: rebuhr
"""

# program gives count of the occurrences of each word in a given phrase.

def word_count(phrase):
    words = phrase.split()
    d = {}
    for word in words:
        if not d.has_key(word):
            d[word] = 1
        else:
            d[word] += 1
    return d
