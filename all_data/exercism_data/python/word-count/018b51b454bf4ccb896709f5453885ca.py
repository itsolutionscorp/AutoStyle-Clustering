import re
from collections import Counter

class Phrase(object):
  def __init__(self, string):
    self._string = string
  
  def word_count(self):
    return Counter(re.findall(r'\w+',self._string.lower()))
