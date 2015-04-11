import re
from collections import Counter

class Phrase(object):
  def __init__(self, sentence):
    self.sentence = sentence

  def word_count(self):
    return Counter(re.findall(r"\w+", self.sentence.lower()))
