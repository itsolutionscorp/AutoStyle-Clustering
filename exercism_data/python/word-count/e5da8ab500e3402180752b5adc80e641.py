import re
from collections import Counter

class Phrase(object):
    def __init__(self, phrase):
        self.phrase = phrase.lower()
        self.words = self.parsed_words()
    
    def word_count(self):
        return Counter(self.words)
    
    def parsed_words(self):
        return filter(None, re.split('\W+', self.phrase))
