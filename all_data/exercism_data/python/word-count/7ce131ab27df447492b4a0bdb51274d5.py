from collections import Counter
import re

class Phrase(object):
    def __init__(self, word):
        self.word = word

    def word_count(self):
        c = Counter(filter(lambda x: x, [re.sub(r'\W', '', w) for w in self.word.lower().split()]))
        return c
