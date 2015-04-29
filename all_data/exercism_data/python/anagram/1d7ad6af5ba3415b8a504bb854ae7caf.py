def  cleanup(word):
  return ''.join(sorted(word.lower()))

class Anagram(object):
  def __init__(self, word):
    self.word = word
    self.cleaned = cleanup(word)

  def match(self, words):
    return [x for x in words if cleanup(x) == self.cleaned and x != self.word]
