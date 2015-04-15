from collections import Counter
import re

class Phrase(object):
  def __init__(self, phrase):
    self._word_count = dict(Counter(re.findall('\w+', phrase.lower())))

  def word_count(self):
    return self._word_count
