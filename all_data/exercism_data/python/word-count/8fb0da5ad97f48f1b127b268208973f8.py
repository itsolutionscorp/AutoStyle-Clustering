import collections
import string


class Phrase(object):
    def __init__(self, phrase):
        self.phrase = phrase

    def _extract_words(self):
        for word in self.phrase.split():
            word = word.lower().strip(string.punctuation)
            if word:
                yield word

    def word_count(self):
        return collections.Counter(self._extract_words())
