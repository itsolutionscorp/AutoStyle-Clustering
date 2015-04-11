class Phrase(object):
  def __init__(self, phrase):
    self.phrase = phrase

  def word_count(self):
    from collections import Counter
    from re import split
    words = split("\W+", self.phrase.lower())
    counts = Counter(words)
    del counts[''] # Ugly hack. Why does this happen?
    return counts
