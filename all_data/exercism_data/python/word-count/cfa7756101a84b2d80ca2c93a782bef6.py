from collections import Counter
import re


class Phrase(object):
    """Store a phrase and compute various statistics about it"""
    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        """Normalize case, remove punctuation and count words"""
        all_lower = self.phrase.lower()
        no_punctuation = re.sub(r'[^a-zA-Z0-9\s]', '', all_lower)
        return Counter(no_punctuation.split())
