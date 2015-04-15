import re

class Phrase:
  def __init__(self, phrase):
    self.phrase = phrase.lower()

  def word_count(self):
    word_count = {}
    for word in self._words():
      if word in word_count:
        word_count[word] += 1
      else:
        word_count[word] = 1
    return word_count

  def _words(self):
    return re.compile("\w+").findall(self.phrase)
