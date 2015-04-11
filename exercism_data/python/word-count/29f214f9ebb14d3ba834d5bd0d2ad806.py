import re

class Phrase:
  def __init__(self, phrase):
    self.wordRegex = re.compile('\w+', re.I)
    self.phrase = phrase

  def word_count(self):
    words = self.wordRegex.findall(self.phrase)
    result = {}
    for word in words:
      word = word.lower()
      if word in result:
        result[word] += 1
      else:
        result[word] = 1
    return result
