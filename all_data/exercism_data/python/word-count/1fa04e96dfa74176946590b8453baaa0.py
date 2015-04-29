import re
from collections import Counter

class Phrase:
  def __init__(self, sentence):
    self.__sentence = sentence.lower()

  def word_count(self):
    return Counter(self.__words())

  def __words(self):
    return re.findall("\w+", self.__sentence)
    
