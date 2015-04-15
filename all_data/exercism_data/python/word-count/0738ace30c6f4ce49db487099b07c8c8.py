from collections import Counter
from re import findall

class Phrase(object):
    def __init__(self, phrase):
        self.phrase = phrase
    
    def word_count(self):
        return Counter(i.lower() for i in findall("\w+", self.phrase))
