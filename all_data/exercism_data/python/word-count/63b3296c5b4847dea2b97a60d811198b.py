from collections import Counter
import re


class Phrase(object):
    """Represent a phrase."""

    __word_re = re.compile(r"\w+", re.UNICODE)

    def __init__(self, phrase):
        self.phrase = phrase

    def words(self):
        """Return an iterator of the words, i.e. sequences composed of
        alphanumeric characters and underscores ("_"), in the phrase.
        """
        return (m.group(0) for m in self.__word_re.finditer(self.phrase))

    def word_count(self):
        """Return a dict (a Counter, really) where the keys are the
        words in the phrase, normalized to lower case, and the values are
        the frequencies of these words in the phrase.
        """
        return Counter(word.lower() for word in self.words())
