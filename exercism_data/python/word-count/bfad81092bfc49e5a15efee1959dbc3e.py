from collections import Counter


def _strip_punctuation(str_):
    return "".join(c for c in str_ if c.isalnum() or c.isspace())


class Phrase(object):

    def __init__(self, input_):
        words = _strip_punctuation(input_)
        self._words = words.lower()

    def word_count(self):
        return Counter(self._words.split())
