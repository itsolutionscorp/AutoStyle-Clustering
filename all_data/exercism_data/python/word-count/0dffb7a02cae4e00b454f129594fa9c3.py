"""
A module for counting the number of occurences of words
in a phrase.
"""
import string
from collections import Counter


class Phrase(object):
    """
    A phrase object for standardizing phrases and counting word
    occurences.
    """

    def __init__(self, phrase):
        self.phrase = phrase

    @property
    def normalized_phrase(self):
        trans = string.maketrans(string.uppercase, string.lowercase)
        return string.translate(self.phrase, trans, string.punctuation)

    def _get_words(self):
        return self.normalized_phrase.split()

    def word_count(self):
        return dict(Counter(self._get_words()))
