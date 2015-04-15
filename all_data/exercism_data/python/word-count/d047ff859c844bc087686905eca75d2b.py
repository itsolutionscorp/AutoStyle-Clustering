__author__ = 'angelo'
from collections import Counter
from string import maketrans, punctuation

class Phrase():

    def __init__(self, phrase):
        self.phrase = phrase.lower().translate(maketrans("", ""), punctuation)
        print self.phrase

    def word_count(self):
        c = Counter()
        for word in self.phrase.split():
            c[word] += 1
        return c
