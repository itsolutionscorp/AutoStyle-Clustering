from collections import Counter
import re


class Phrase(object):

    def __init__(self, phrase):
        self.words = re.findall("\w+", phrase.lower())

    def word_count(self):
        count = Counter(self.words)
        return count
