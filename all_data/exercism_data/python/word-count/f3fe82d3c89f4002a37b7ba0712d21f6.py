from collections import Counter
import re

class Phrase:
    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        return Counter(filter(None, re.split('\W+', self.phrase.lower())))
