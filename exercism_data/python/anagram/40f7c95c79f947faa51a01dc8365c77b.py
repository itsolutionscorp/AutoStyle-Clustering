from collections import Counter

def count(word):
    return Counter(word.lower())

class Anagram(object):
    def __init__(self, word):
        self.word = word
        self.counts = count(word)
        
    def match(self, words):
        return [word for word in words if word != self.word and count(word) == self.counts]
