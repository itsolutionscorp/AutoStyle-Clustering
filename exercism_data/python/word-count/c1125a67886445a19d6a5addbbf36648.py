import re
from collections import Counter


class Phrase(object):

    """Object that holds information about a phrase."""

    WORD_PATTERN = re.compile(r'\w+')

    def __init__(self, text):
        self._text = text

    def word_count(self):
        """Return a dictionary with word as key and count as value."""
        return Counter(self._separate_words())

    def _separate_words(self):
        """Return an array with words from text."""
        return re.findall(self.WORD_PATTERN, self._text.lower())
