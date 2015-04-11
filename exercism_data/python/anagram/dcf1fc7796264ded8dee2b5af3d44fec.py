import string

class Anagram(object):

    def __init__(self, word):
        self.word = word

    def match(self, words):
        return filter(self.is_anagram, words)

    def is_anagram(self, word):
        return normalize(self.word) != normalize(word) \
               and cannonize(self.word) == cannonize(word)

def normalize(word):
    """for word comparisons"""
    return string.join([char for char in word.lower() 
                       if char not in string.punctuation
                       and char not in string.whitespace])

def cannonize(word):
    """for anagram comparisons"""
    return string.join(sorted(normalize(word)),'')
