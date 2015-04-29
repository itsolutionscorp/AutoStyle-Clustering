#!/usr/bin/env python

class Anagram:
    def __init__(self, anagram):
        self.anagram = anagram.lower()

    def match(self, word_list):
        anagram_list = []
        for word in word_list:
            if sorted(word.lower()) == sorted(self.anagram) and not word.lower() == self.anagram:
                anagram_list.append(word)
        return anagram_list
