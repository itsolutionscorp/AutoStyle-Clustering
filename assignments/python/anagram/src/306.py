# -*- coding: utf-8 -*-
#
# Write a program that, given a word and a list of possible anagrams,
#  selects the correct sublist.
#
# Given '"listen"' and a list of candidates like '"enlists" "google" "inlets" "banana"'
#  the program should return a list containing
#  '"inlets"'.
#
def detect_anagrams(real_str, strings):
    #create master list    
    target = sorted(list(real_str.lower()))
    #lose the same string    
    short_strings = [s for s in strings if s.lower() != real_str.lower()]
    matches = []
    #iterate through sorted strings elements with if
    for str in short_strings:
        if sorted(list(str.lower())) == target:
            matches.append(str)
    #need to return the original strings as a list
    return matches
