import collections
import string


class Phrase(object):
    def __init__(self, words):
        words = map(str.lower, words.split())
        stripped_words = filter(bool, map(lambda x: x.strip(string.punctuation), words))
        self._counts = collections.Counter(stripped_words)

    def word_count(self):
        return dict(self._counts)
