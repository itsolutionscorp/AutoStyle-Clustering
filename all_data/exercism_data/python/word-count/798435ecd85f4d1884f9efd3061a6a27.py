import re
import collections

class Phrase(object):
  
    def __init__(self, sentence):
        self.sentence = sentence
    
    def word_count(self):
        return collections.Counter(self._words())
        
    def _words(self):
        return re.findall(self._words_pattern(), self.sentence.lower())

    def _words_pattern(self):
        return '\w+'
