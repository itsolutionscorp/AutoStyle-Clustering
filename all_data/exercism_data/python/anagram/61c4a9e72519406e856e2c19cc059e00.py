"""For a given string, return elements from a supplied list that are
   anagrams of that string."""

import collections

class Anagram(object):
    def __init__(self, *args):
        self.source_word = args[0]

    def match(self, wordlist):
        matches = []
        for word in wordlist:
            if word == self.source_word:
                '''skip it if anagram and src are the same'''
                continue
            x = [x.lower() for x in word]
            y = [y.lower() for y in self.source_word]
            if sorted(x) == sorted(y):
                '''We have the same letters in both words'''
                matches.append(word)
        return matches
