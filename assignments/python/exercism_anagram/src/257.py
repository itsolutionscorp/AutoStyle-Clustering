#!/usr/bin/env python3
#-*- coding: utf-8 -*-
__author__ = "Greg"
def detect_anagrams(base_word, possible_anagrams):
    """
    compares string base_word to list of strings possible_anagrams for
    for anagrams, ignoring capitalization, excluding exact matches. 
    Returns list of strings.
    """
    solutions = []
    base_word = base_word.lower()
    sorted_base_word = sorted(base_word)
    
    for x in range(len(possible_anagrams)):
        
        if ((sorted(possible_anagrams[x].lower()) == sorted_base_word) and 
           ((   possible_anagrams[x].lower())     != (base_word))):
            solutions.append(possible_anagrams[x])
    
    return solutions
