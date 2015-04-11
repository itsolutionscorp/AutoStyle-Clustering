import re
from collections import defaultdict


class Phrase(object):

    """Object that holds information about a phrase."""

    WORD_PATTERN = re.compile(r'\w+')

    def __init__(self, text):
        self._text = text
        self._words = defaultdict(int)

    def word_count(self):
        """Return a dictionary with word as key and count as value."""
        for word in self._separate_words():
            self._words[word.lower()] += 1
        return self._words

    def _separate_words(self):
        """Return an array with words from text."""
        return re.findall(self.WORD_PATTERN, self._text)
