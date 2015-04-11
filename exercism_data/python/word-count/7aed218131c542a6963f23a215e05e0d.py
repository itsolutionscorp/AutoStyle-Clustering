import collections
import re


class Phrase(object):
    def __init__(self, text):
        self.words = filter(None, re.split(r'\W+', text))

    def word_count(self):
        counts = collections.defaultdict(int)
        for word in self.words:
            counts[word.lower()] += 1
        return dict(counts)
