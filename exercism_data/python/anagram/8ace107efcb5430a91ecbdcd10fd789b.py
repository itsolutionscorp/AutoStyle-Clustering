from collections import Counter

class Anagram(object):
  def __init__(self, source):
    self._source = Word(source)

  def match(self, targets):
    return [target for target in targets
            if self._source.is_anagram_of(target)]


class Word(object):
  def __init__(self, word):
    self._raw = word.lower()
    self._standard_form = Counter(self._raw)

  def is_anagram_of(self, other):
    if not isinstance(other, Word):
      other = Word(other)
    return self._standard_form == other._standard_form\
      and self._raw != other._raw
