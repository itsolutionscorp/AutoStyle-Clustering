import re
from collections import defaultdict


class Phrase:
    def __init__(self, phrase):
        self.phrase = phrase.lower()

    def word_count(self):
        word_count = defaultdict(int)

        for word in re.findall(r'(\w+)', self.phrase):
            word_count[word] += 1

        return word_count
