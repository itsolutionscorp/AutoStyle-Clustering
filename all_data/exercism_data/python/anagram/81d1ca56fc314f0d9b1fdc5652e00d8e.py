import string

class Anagram(object):

    def __init__(self, word):
        self.word = word

    def match(self, words):
        return filter(self.is_match, words)

    def is_match(self, word):
        return self.is_different(word) and self.is_anagram(word)

    def is_different(self, word):
        return normalize(self.word) != normalize(word)

    def is_anagram(self, word):
        return cannonize(self.word) == cannonize(word)

def normalize(word):
    """for word comparisons"""
    return string.join([char for char in word.lower() 
                       if char not in string.punctuation
                       and char not in string.whitespace])

def cannonize(word):
    """for anagram comparisons"""
    return string.join(sorted(normalize(word)),'')
