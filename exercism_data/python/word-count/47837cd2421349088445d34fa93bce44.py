import string
import collections

class Phrase(object):
  
    def __init__(self, sentence):
        self.sentence = sentence
    
    def word_count(self):
        counter = collections.Counter()
        for word in self._words():
            counter[word] +=1
        return counter

    def _words(self):
        return filter(None, [word.lower().strip(string.punctuation) for word in self.sentence.split()])
