import re
import collections

class Phrase(object):
  
    def __init__(self, sentence):
        self.sentence = sentence
    
    def word_count(self):
        return collections.Counter(self._words())
        

    def _words(self):
        return filter(None, [word.lower() for word in re.split('\W+', self.sentence)])
