__author__ = 'angelo'


class Anagram():

    def __init__(self, word):
        self.word = word.lower()

    def match(self, l):
        return [x for x in l if sorted(x.lower()) == sorted(self.word) and x.lower() != self.word]
