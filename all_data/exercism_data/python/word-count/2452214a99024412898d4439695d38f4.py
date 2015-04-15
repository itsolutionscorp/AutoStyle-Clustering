import collections
import re

class Phrase:
    def __init__(self, phrase):
        self.phrase = phrase
        self._normalize()

        counter = collections.Counter()
        for word in self._get_words():
            counter[word] += 1
            continue

        self.counts = dict(counter)
        return

    def word_count(self):
        return self.counts

    def _get_words(self):
        return self.phrase.split()

    def _normalize(self):
        self.phrase = Phrase.re_not_word.sub(" ", self.phrase.lower())
        return

    re_not_word = re.compile(r'[^a-z0-9]+')

    pass
