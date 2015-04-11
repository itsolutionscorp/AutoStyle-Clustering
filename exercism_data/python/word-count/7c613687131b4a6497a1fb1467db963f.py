import re

class Phrase:
  def __init__(self, phrase):
    self.phrase = phrase.lower()

  def word_count(self):
    word_count = {}
    for word in self._words():
      word_count[word] = word_count.get(word, 0) + 1
    return word_count

  def _words(self):
    return re.compile("\w+").findall(self.phrase)
