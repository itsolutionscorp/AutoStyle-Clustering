import re
import collections

__all__ = ["Phrase"]

words_re = re.compile(r"\w+")

def words(s):
    return words_re.findall(s)

class Phrase(object):
    def __init__(self, phrase):
        self._phrase = phrase
        self._word_count = None

    def word_count(self):
        if self._word_count is None:
            self._word_count = collections.Counter(words(self._phrase.lower()))

        return self._word_count
