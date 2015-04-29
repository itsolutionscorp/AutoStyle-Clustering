# -*- coding: utf-8 -*-
"""
Created on Mon Oct  6 16:30:40 2014

@author: napopa
"""

def detect_anagrams(word,sentence):
    list_sentence = list(sentence)
    sorted_word = ''.join(sorted(word.lower()))
    result = []    
    
    for var in list_sentence:
        if ''.join(sorted(var.lower())) == sorted_word and var.lower() != word.lower():
            result.append(var)
    return result
