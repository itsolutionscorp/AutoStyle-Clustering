#!/usr/bin/env python3
#-*- coding: utf-8 -*-

__author__ = "Greg"

def detect_anagrams(word, possible_anagrams):
    """
    compares string word to list of strings possible_anagrams for anagrams, 
    ignoring capitalization, excluding exact order matches. Returns list of 
    strings.
    """
    
    matches = []
    word = word.lower()
    sort_word = sorted(word)
    
    for x in range(len(possible_anagrams)):
        lower_possible = possible_anagrams[x].lower()
        
        if (sorted(lower_possible) == sort_word and 
                   lower_possible  !=      word):
            matches.append(possible_anagrams[x])
    
    return matches
