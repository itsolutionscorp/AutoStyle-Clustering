import re
from collections import defaultdict


class Phrase():
    def __init__(self, phrase):
        self._phrase = phrase.lower()
        self._split_words = re.findall(r"\w+", phrase.lower())
        self._word_counts = defaultdict(int)

    def word_count(self):
        for word in self._split_words:
            self._word_counts[word] += 1
        return self._word_counts
