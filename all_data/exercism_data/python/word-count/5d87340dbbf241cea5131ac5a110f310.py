import re

class Phrase(object):
  def __init__(self, phrase):  
    words = re.split('\W+', phrase)
    self.dict = {}
    for word in words:
      if word:
        self.dict[word.lower()] = self.dict.setdefault(word.lower(), 0) + 1
  
  def word_count(self):
   return self.dict
    
