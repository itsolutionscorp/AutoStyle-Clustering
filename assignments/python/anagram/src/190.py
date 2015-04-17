# -*- coding: utf-8 -*-
"""
Created on Thu Sep 25 14:01:08 2014
@author: laegrim
"""
def detect_anagrams(word, possible_anagrams):
    '''
    Determines which words, if any, in the possible anagrams list are anagrams 
    of the given word
    '''
    
    #iterate through possible anagrams
    #if the list of letters in the anagram and word are the same and 
    #the anagram isn't the word, we are good to go
    return [anagram for anagram in possible_anagrams if \
    sorted(i for i in anagram.lower()) == sorted(i for i in word.lower()) \
    and anagram.lower() != word.lower()]
