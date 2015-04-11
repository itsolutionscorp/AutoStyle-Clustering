#! /usr/bin/env python 

from collections import Counter
import string

class Phrase(object):

    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        phrase_list = self.clean_phrase()
        return dict(Counter(phrase_list))

    def clean_phrase(self):
        # This function takes a phrase, normalizes case, removes punctuation, 
        # and then splits into and returns a list of the words in the phrase.

        return self.phrase.lower().translate(None,string.punctuation).split()
