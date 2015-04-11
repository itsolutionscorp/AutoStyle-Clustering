#!/usr/bin/env python
from collections import defaultdict
import string

class Phrase(object):
    phrase = ''

    def __init__(self, input_string):
        self.phrase = input_string

    def word_count(self):
        phrase = self.remove_punctuation()

        output = defaultdict(int)
        for word in phrase.lower().split():
            output[word] += 1 
        return output

    def remove_punctuation(self):
        punct = set(string.punctuation)
        return ''.join(char for char in self.phrase if char not in punct)
