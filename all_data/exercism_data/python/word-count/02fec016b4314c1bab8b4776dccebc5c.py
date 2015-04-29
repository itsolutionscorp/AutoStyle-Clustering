from collections import Counter
from re import findall


class Phrase(object):
  def __init__(self, phrase):
    self.phrase = phrase

  def word_count(self):
    words = findall("\w+", self.phrase.lower())
    counts = Counter(words)
    return counts
