from collections import Counter
import re

class Phrase(object):
    def __init__(self, phrase):
        self.phrase = phrase

    def words(self):
        return re.findall("\w+", self.phrase)

    def word_count(self):
        return Counter(x.lower() for x in self.words())
