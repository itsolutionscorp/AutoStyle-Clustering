from collections import Counter
import re


class Phrase(object):
    def __init__(self, phrase):
        self._phrase = phrase
        self._occurences = None

    def word_count(self):
        if self._occurences is None:
            self._occurences = self._calculate_occurences()
        return self._occurences

    def _calculate_occurences(self):
        occurences = Counter(self._tokenize(self._phrase))
        return dict(occurences.items())

    def _tokenize(self, input):
        normalized = input.lower()
        return re.findall(r"\w+", normalized)

    @property
    def phrase(self):
        return self._phrase

    def __str__(self):
        return "Phrase<%s>" % (self._phrase,)
