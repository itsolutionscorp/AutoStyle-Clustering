#!/usr/bin/env python3
""" module difference of squares for exercism.io programming training"""

def find_anagram(master, candidate, subcall=False):
    # check for presence of char, remove this and recurse
    if master == candidate and not subcall:
        return False
    if not len(master) == len(candidate):
        return False
    char_index = candidate.find(master[0])
    if char_index > -1:
        if len(master) > 1:
            new_cand = ''.join([candidate[:char_index], candidate[char_index+1:]])
            return find_anagram(master[1:], new_cand, subcall=True)
        else:
            return True
    else:
        return False

def detect_anagrams(master, candidates):

    anagrams = []
    for candidate in candidates:
        if find_anagram(master.lower(), candidate.lower()):
            anagrams.append(candidate)
    return anagrams
