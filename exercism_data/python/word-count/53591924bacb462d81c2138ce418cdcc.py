from collections import Counter
import re


def _tokenize(input):
    normalized = input.lower()
    return re.findall(r"\w+", normalized)


class Phrase(object):
    def __init__(self, phrase):
        self._phrase = phrase
        self._occurrences = None

    def word_count(self):
        if self._occurrences is None:
            self._occurrences = self._calculate_occurrences()
        return self._occurrences

    def _calculate_occurrences(self):
        occurrences = Counter(_tokenize(self._phrase))
        return dict(occurrences.items())

    @property
    def phrase(self):
        return self._phrase

    def __str__(self):
        return "Phrase<%s>" % (self._phrase,)
