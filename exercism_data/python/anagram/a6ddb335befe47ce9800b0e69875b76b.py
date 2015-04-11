#! /usr/bin/env python

class Anagram(object):
    '''
    Given a word and a list of possible anagrams, returns the anagram.
    '''
    def __init__(self,word):
        self.word = word

    def match(self,anagrams):
        return [ anagram for anagram in anagrams if anagram != self.word and self._word_to_sorted_list(anagram) == self._word_to_sorted_list(self.word) ]

    def _word_to_sorted_list(self,word):
        return sorted(list(word.lower()))

            
