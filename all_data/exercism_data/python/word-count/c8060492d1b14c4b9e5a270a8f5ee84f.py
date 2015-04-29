# -*- coding: utf-8 -*-
"""
Created on Mon Oct 27 22:46:36 2014

@author: DiCar
"""

def word_count(phrase):
    dictionary = {}
    
    words = phrase.split()
    for word in words:
        dictionary[word] = words.count(word)
    return dictionary
    
