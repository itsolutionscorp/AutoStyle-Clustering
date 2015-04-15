# -*- coding: utf-8 -*-
"""
Created on Thu Sep 25 15:39:52 2014

@author: johann
"""

def detect_anagrams(word,list):
    anagrams = []
    for element in list:
        if is_anagram(word,element):
            anagrams.append(element)
    return anagrams
            
def is_anagram(word,element):
    word = word.lower()
    element = element.lower()
    if word == element:
        return False
    word = sorted(word)
    element = sorted(element)
    return word == element
     
    
