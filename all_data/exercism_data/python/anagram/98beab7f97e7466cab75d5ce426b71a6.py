import string

class Anagram(object):

    def __init__(self, word):
        self.word = word

    def match(self, words):
        return filter(self.is_anagram, words)

    def is_anagram(self, word):
        return self.word != word \
               and cannonize(self.word) == cannonize(word)

def cannonize(word):
    """for anagram comparisons"""
    matchable_chars = [char for char in word.lower() 
                       if char not in string.punctuation
                       and char not in string.whitespace]
    return string.join(sorted(matchable_chars),'')
