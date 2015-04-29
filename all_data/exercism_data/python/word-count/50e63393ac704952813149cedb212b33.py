"""
A module for counting the number of occurences of words
in a phrase.
"""
import re
from collections import Counter


class Phrase(object):
    """
    A phrase object for standardizing phrases and counting word
    occurences.
    """
    ignore_chars = re.compile(r'[^\w\s]', re.I)

    def __init__(self, phrase):
        self.phrase = phrase

    @property
    def normalized_phrase(self):
        phrase = self.phrase.lower()
        return re.sub(self.ignore_chars, '', phrase)

    def _get_words(self):
        return self.normalized_phrase.split()

    def word_count(self):
        return dict(Counter(self._get_words()))
