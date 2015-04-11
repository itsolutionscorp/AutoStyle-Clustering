__author__ = 'Adrian Rivera'


from collections import Counter
import re

class Phrase(object):

    def __init__(self, phrase):
        cleaned_phrase = ''.join([c for c in phrase if self.is_accepted(c)])
        self.counter = Counter(re.split('\W+', cleaned_phrase.lower()))

    def is_accepted(self, ch):
        return ch.isalpha() or ch == ' ' or ch.isdigit()

    def word_count(self):
        return self.counter
