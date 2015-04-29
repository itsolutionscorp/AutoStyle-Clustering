from collections import Counter
import re

class Phrase(object):

    def __init__(self, phrase):
        self.phrase = phrase or ''
        
    def word_count(self):   
        return Counter( re.findall("(\w+)", self.phrase.lower()) )
