import re
from collections import Counter

class Phrase(object):

    def __init__(self, phrase):
        self._phrase = phrase

    def word_count(self):
        """Counts the number of times each word occurs in the phras.

        Returns:
            A dict mapping each word to the number of times it occurs.
        """

        words = filter(None, re.split('\W', self._phrase.lower()))

        return Counter(words)
    
