import re
from collections import Counter

class Phrase(object):

    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        words = re.split(r"\W+", self.phrase)
        words = filter(None, words) # get rid of empty strings
        counts = Counter()
        for word in words:
            counts[word.lower()] += 1
        return counts
