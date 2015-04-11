import re

class Phrase(object):
  def __init__(self, phrase):
    super(Phrase, self).__init__()
    self.phrase = phrase

  @property
  def words(self):
    return map(lambda x: x.lower(), re.findall("\w+", self.phrase))
    
  def word_count(self):
    return dict(map(self.count_word, set(self.words)))

  def count_word(self, word):
    return [word, self.words.count(word)]
