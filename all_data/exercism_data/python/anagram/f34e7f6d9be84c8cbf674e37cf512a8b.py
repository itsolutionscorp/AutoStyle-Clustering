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

    def match(self, anagram_list):
        signature = sorted(self.word.lower())

        result = filter(lambda item: sorted(item.lower()) == signature, anagram_list)
        result = filter(lambda item: item != self.word, result)

        return result
