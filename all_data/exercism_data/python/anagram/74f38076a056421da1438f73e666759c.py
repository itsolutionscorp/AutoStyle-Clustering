from collections import Counter

class Anagram(object):
  def __init__(self, word):
    self._word = word
  
  def match(self, others):
    return [o for o in others if self._is_anagram(Anagram(o))]

  def _is_anagram(self, other):
    return (other._normalized() != self._normalized() and
            other._letters() == self._letters())
  
  def _letters(self):
    return Counter(self._normalized())
    
  def _normalized(self):
    return self._word.lower()
