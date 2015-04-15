from collections import defaultdict
import re

class Phrase(object):
    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        words = defaultdict(int)
        for word in re.findall(r'[\w]+', self.phrase):
            words[word.lower()] += 1
        return words
