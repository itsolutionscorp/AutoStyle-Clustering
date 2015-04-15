import collections


class Anagram(object):

    def __init__(self, word):
        self.word = word.strip()

    def match(self, words):
        letters = collections.Counter(self.word.lower())
        return filter(lambda x: x != self.word and collections.Counter(x.lower()) == letters, words)
