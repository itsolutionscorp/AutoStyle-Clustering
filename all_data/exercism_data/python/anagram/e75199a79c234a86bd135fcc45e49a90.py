from collections import Counter

class Anagram(object):
    def __init__(self, word):
        self.source = word
        self.counter = Counter(word.lower())

    def match(self, words):
        return [word for word in words if self.is_match(word)]

    def is_match(self, word):
        return word != self.source and Counter(word.lower()) == self.counter
