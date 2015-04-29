#! /usr/bin/env python 

from collections import Counter
import string

class Phrase(object):
    '''
    Returns a Counter dictionary of the number of occurrences of each word
    in a provided phrase.
    '''
    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        word_list = self._clean()
        return Counter(word_list)

    def _clean(self):
        '''
        Takes the phrase, normalizes case, removes punctuation, 
        and then splits into and returns a list of the words in the phrase.
        '''
        table = string.maketrans('','')
        return self.phrase.lower().translate(table,string.punctuation).split()
