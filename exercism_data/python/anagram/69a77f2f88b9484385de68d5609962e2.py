from collections import Counter
class Anagram(object):
  def __init__(self, source):
    self.source = Word(source)

  def match(self, targets):
    return [target for target in targets \
            if self.source.is_anagram_of(target)]

class Word(object):
  def __init__(self, word):
    self.really_raw = word  # The two hard things in programming...
    self.raw = word.lower()
    self.standard_form = Counter(list(self.raw))

  def is_anagram_of(self, other):
    if type(other) != "Word": other = Word(other)
    return self.standard_form == other.standard_form\
      and not self.raw == other.raw

  def string(self): return self.really_raw
