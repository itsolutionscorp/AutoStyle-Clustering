#!/usr/bin/env python

"""
Anagram

Write a program that, given a word and a list of possible anagrams, 
selects the correct sublist.

Given `"listen"` and a list of candidates like `"enlists" "google" 
"inlets" "banana"` the program should return a list containing `"inlets"`.
"""

class Anagram(object):
    def __init__(self, word):
        self.word = word
        self.signature = sorted(self.word.lower())

    def is_anagram(self, item):
        return sorted(item.lower()) == self.signature and item != self.word 

    def match(self, anagram_list):
        result = filter(self.is_anagram, anagram_list)

        return result
