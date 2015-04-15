from collections import Counter
import re

class Phrase(object):
    def __init__(self, phrase):
        self.phrase = phrase

    def words(self):
        words = re.findall("\w+", self.phrase)
        return [w.lower() for w in words]

    def word_count(self):
        return Counter(self.words())
