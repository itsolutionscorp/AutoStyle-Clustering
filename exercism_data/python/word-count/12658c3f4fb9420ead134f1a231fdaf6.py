from collections import Counter
import re


def frequencies(collection):
    """
    Count number of occurences of every element in a collection. 
    Return a map where keys are elements and values are respective
    numbers of occurences.

    >>> frequencies(["aaa", "bbb", 2, 4, "bbb", 2])
    {2: 2, 4: 1, 'aaa': 1, 'bbb': 2}
    """
    collection = list(collection)
    counter = Counter()
    for element in collection:
        counter[element] += 1
    return dict(counter.items())


class Phrase(object):
    def __init__(self, phrase):
        self._phrase = phrase
        self._occurences = None

    def word_count(self):
        if self._occurences is None:
            self._occurences = self._calculate_occurences()
        return self._occurences

    def _calculate_occurences(self):
        return frequencies(self._tokenize(self._phrase))

    def _tokenize(self, input):
        normalized = input.lower()
        return re.findall(r"\w+", normalized)

    @property
    def phrase(self):
        return self._phrase

    def __str__(self):
        return "Phrase<%s>" % (self._phrase,)
