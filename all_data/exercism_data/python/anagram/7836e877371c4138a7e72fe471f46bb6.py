from collections import Counter
class Anagram(object):
  def __init__(self, source):
    self.raw_source = source.lower()
    self.source = self._standard_form(source)

  def match(self, targets):
    return [target for target in targets if self._is_anagram(target)]

  def _is_anagram(self, target):
    return self.source == self._standard_form(target) and not \
    self.raw_source == target.lower()

  def _standard_form(self, word):
    return Counter(word.lower()) # Should I use this or sorted?
