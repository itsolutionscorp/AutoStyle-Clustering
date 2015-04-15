"""Word Counting."""

from collections import Counter
import re
import string


# A regular expression that matches any punctuation character.
PUNCTUATION_REGEX = re.compile("[{}]".format(re.escape(string.punctuation)))


class Phrase(str):
    """A subclass of str that supports word counting."""

    def __init__(self, phrase=''):
        super(Phrase, self).__init__(phrase)
        self._counter = None

    def __repr__(self):
        return "{!s}({!r})".format(self.__class__.__name__, str(self))

    def word_count(self):
        """Return a word frequency dictionary.

        A word is delimited by runs of consecutive whitespace or punctuation.
        """
        if self._counter is None:
            punctuation_erased = re.sub(PUNCTUATION_REGEX, ' ', self)
            self._counter = Counter(
                word.lower() for word in punctuation_erased.split())
        return self._counter
