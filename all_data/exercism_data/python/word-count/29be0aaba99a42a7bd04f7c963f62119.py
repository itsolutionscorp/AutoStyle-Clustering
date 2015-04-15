from collections import Counter
import re


class Phrase:
    def __init__(self, phrase):
        self.words = re.sub(r"[^\w ]+", '', phrase.lower()).split()

    def word_count(self):
        return Counter(self.words)
