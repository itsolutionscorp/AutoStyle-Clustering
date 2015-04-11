#!/usr/bin/python
import re

class Phrase:
   def __init__(self, phrase):
      self.phrase  = re.sub(r"[^\s\w\d]", "", phrase)
      self.wordmap = {}
      
      # count
      for word in [x.lower() for x in self.phrase.split()]:
         if word in self.wordmap:
            self.wordmap[word] += 1
         else:
            self.wordmap[word] = 1

   def word_count(self):
      return self.wordmap
