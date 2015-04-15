import collections
import string
import re

class Phrase:

  def __init__(self, str):
    str = str.lower()
    punc = r"[%(punc)s]" % {'punc' : string.punctuation}
    str = re.sub(punc, "", str)
    array = str.split()
    self.histogram = collections.Counter(array)

  def word_count(self):
    return self.histogram
