import re
from collections import Counter

class Phrase(object):
  def __init__(self, words):
    self.words = words

  def word_count(self):
    return Counter(re.findall(r"\w+", self.words.lower()))
