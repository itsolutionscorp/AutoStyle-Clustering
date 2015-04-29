#!/usr/bin/env python2
# -*- coding: utf-8 -*-

def word_count(phrase):
    # Split at each whitespace
    splitted_phrase = phrase.split()
    
    # Dictionary to contain the word count
    finaldict = {}
    for w in splitted_phrase:
        if w in finaldict:
            # if the word, "i", is already in finaldict, add 1
            finaldict[w] += 1
        else:
            finaldict[w] = 1

    return finaldict
