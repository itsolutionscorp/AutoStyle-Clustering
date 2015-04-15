from collections import Counter
import re


class Phrase(object):
    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        return Counter(word.lower() for word in re.findall(r'\w+', self.phrase))
