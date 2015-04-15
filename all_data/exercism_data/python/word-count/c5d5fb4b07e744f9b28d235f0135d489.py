#!/usr/bin/env python2
# -*- coding: utf-8 -*-

def word_count(phrase):
    # Strip out newlines
    p = phrase.strip('\n')
    # Split at each whitespace
    s = p.split()
    
    # Dictionary to contain the word count
    d = {}
    for i in s:
        if i in d:
            # if the word, "i", is already in d, add 1
            d[i] += 1
        else:
            d[i] = 1

    return d
