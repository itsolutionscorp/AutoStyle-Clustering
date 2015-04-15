class Anagram(object):
  def __init__(self, text):
    self.text = text.lower() 

  def match(self, words):
    return [word for word in words if self.matched(word)]

  def sort(self, word):
    return sorted(word.lower())

  def matched(self, word):
    return self._anagram_of(word) and self._not_the_same(word)

  def _not_the_same(self, word):
    return self.text != word

  def _anagram_of(self, word):
    return self.sort(word) == self.sort(self.text)
