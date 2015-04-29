#!/usr/bin/env python
# -*- coding: utf-8 -*-

def word_count(sentence):
    word_dict = {}
    words = sentence.split()

    for word in words:
        word_dict[word] = word_dict.get(word, 0) + 1
    return word_dict
