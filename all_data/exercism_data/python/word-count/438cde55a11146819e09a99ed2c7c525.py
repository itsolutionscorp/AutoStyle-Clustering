import re

class Phrase(object):
  def __init__(self, phrase):
    self.phrase = phrase
    self.words = re.findall(r"[\w']+", self.phrase.lower())

  def word_count(self):
    count = {}
    for word in self.words:
      if word in count:
        count[word] += 1
      else:
        count[word] = 1
    return count
