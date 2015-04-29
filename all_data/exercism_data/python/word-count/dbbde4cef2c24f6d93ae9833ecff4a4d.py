from collections import Counter
import re

class Phrase(object):
  def __init__(self, phrase):
    self._word_count = dict(Counter(re.split('\W+', phrase.lower())))
    if '' in self._word_count:
      del self._word_count['']

  def word_count(self):
    return self._word_count
