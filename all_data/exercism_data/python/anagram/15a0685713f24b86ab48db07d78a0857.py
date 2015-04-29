'''
Created on Sep 25, 2014

@author: adsmith
'''

from collections import Counter

def detect_anagrams(word, anagrams):
    return [anagram for anagram in anagrams if 
            Counter(word.lower()) == Counter(anagram.lower()) and
            word.lower() != anagram.lower()]
