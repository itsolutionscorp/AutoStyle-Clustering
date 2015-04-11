from collections import Counter

class Anagram(object):  
  def __init__(self, word):
    self.word = word.lower()
  
  def is_anagram(self, cadidate):
    return Counter(self.word) == Counter(cadidate) and self.word != cadidate
    
  def match(self, candidates):
    return [ word for word in candidates if self.is_anagram(word.lower()) ]
    
  
