from collections import Counter
import re


class Phrase(object):
    """Store a phrase and compute various statistics about it"""
    def __init__(self, phrase):
        """Store the original phrase and a normalized version

        The normalized version has punctuation removed and is all
        lowercase.

        """
        self.phrase = phrase
        self.normalized = re.sub(r'[^a-zA-Z0-9\s]', '', phrase.lower())

    def word_count(self):
        """Count words"""
        return Counter(self.normalized.split())
