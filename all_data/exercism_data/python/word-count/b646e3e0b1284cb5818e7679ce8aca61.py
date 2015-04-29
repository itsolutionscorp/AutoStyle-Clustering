import re
from collections import defaultdict

class Phrase(object):

    def __init__(self, phrase):
        self._phrase = phrase

    def word_count(self):
        """Counts the number of times each word occurs in the phras.

        Returns:
            A dict mapping each word to the number of times it occurs.
        """

        words = re.split('\W', self._phrase.lower())

        d = defaultdict(int)
        for w in words:
            if len(w) > 0:
                d[w] += 1

        return d
