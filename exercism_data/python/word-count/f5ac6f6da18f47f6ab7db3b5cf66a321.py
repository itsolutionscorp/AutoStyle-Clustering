# -*- coding: utf-8 -*-
from collections import defaultdict
import string



class Phrase(object):

  _IGNORE_PATTERN = ',.!@#$%^&*:'
  _PUNCTUATION_TRANFORMATION_TABLE = string.maketrans(_IGNORE_PATTERN, ' ' * len(_IGNORE_PATTERN))

  def __init__(self, sentance):
    self._sentance = sentance

  def word_count(self):
    d = defaultdict(int)
    new_sentace = self._sentance.translate(
        self.__class__._PUNCTUATION_TRANFORMATION_TABLE
    )

    for w in new_sentace.split():
      d[w.lower()] += 1

    return d
