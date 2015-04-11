import re


class Phrase():
    def __init__(self, phrase):
        self._phrase = phrase.lower()
        self._split_words = re.findall("\w+", phrase.lower())
        self._word_counts = {}

    def word_count(self):
        for word in self._split_words:
            self._word_counts[word] = self._word_counts.get(word, 0) + 1
        return self._word_counts
