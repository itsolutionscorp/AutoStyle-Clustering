import re
from collections import defaultdict
class Phrase:
  def __init__(self, sentence):
    self.__sentence = sentence.lower()

  def word_count(self):
    return reduce(self.__increment_word, self.__words(), defaultdict(lambda: 0, {}))

  def __increment_word(self, counts, word):
    counts[word] += 1 
    return counts

  def __words(self):
    return re.findall("\w+", self.__sentence)
    
