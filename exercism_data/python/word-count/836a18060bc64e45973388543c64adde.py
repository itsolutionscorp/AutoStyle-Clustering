import re
from collections import Counter

class Phrase:
  def __init__(self, phrase):
    self.wordRegex = re.compile('\w+', re.I)
    self.phrase = phrase

  def word_count(self):
    words = self.wordRegex.findall(self.phrase)
    result = Counter([word.lower() for word in words])
    return result
