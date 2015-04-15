import re
import collections

class Phrase(object):

  def __init__(self, phrase):
    self.phrase = phrase

  def word_count(self):
    return collections.Counter(self.words())

  def words(self):
    return re.split('[^\w]+',self.ascii_lowercase())

  def ascii_lowercase(self):
    return re.sub('[^\w\s]+','',self.phrase.lower())
