# -*- coding: utf-8 -*-
"""
Created on Wed Sep 24 17:42:58 2014
"""
from collections import Counter
def detect_anagrams(key_word, word_list):
    anagram_list = []
    key_word_counter = Counter(key_word.lower())
    for w in word_list:
        if w.lower() != key_word.lower():
            if key_word_counter == Counter(w.lower()):
                anagram_list.append(w)
    return anagram_list
