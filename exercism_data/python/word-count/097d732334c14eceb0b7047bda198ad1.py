# Dirk Herrmann's solution version 2 for exercism exercise "Word Count"

# This file implements class Phrase:
#
# * A Phrase object is constructed from a string.
#
# * The method word_count returns a dictionary (a Counter, actually).  Keys
#   are strings holding the words from the phrase, values are integer values
#   indicating how many times the word occured in the phrase.  Words are
#   substrings matched by the "\w+" regexp, turned to lower case.
#   Example: "!foo bAr$1 Bar" results in {'1': 1, 'foo': 1, 'bar': 2}

import re
from collections import Counter

class Phrase(object):
   def __init__(self, input_string):
      self._phrase_string = input_string

   def word_count(self):
      normalized = self._phrase_string.lower()
      words = re.findall("\w+", normalized)
      return Counter(words)
