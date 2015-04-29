#!/usr/bin/env python3

def detect_anagrams(word, anagrams):
    sublist = []
    for w in anagrams:
        if w == word:
            return sublist
        if sorted(w.lower()) == sorted(word.lower()):
            sublist.append(w)
    return sublist
