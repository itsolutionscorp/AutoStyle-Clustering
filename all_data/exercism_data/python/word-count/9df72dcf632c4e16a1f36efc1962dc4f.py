# -*- coding: utf-8 -*-
"""Implements the rules in the README.md"""
def word_count(sentence):
    """Function to build a word counter"""
    dictionary = {}
    for word in sentence.split():
        if word in dictionary:
            dictionary[word] += 1
        else:
            dictionary[word] = 1
    return dictionary
